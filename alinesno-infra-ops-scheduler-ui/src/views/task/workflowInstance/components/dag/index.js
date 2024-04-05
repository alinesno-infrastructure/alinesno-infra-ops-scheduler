// const { Cell, Graph } = require('@antv/x6');
// const {
//   defineComponent,
//   ref,
//   provide,
//   toRef,
//   watch,
//   onBeforeUnmount,
//   computed,
//   PropTypes
// } = require('vue');
// const { useI18n } = require('vue-i18n');
// const { useRoute } = require('vue-router');
// const DagToolbar = require('./dag-toolbar');
// const DagCanvas = require('./dag-canvas');
// const DagSidebar = require('./dag-sidebar');
// const Styles = require('./dag.module.scss');
// const DagAutoLayoutModal = require('./dag-auto-layout-modal');
// const {
//   useGraphAutoLayout,
//   useGraphBackfill,
//   useDagDragAndDrop,
//   useTaskEdit,
//   useBusinessMapper,
//   useNodeMenu,
//   useNodeStatus
// } = require('./dag-hooks');
// const { useThemeStore } = require('@/store/theme/theme');
// const VersionModal = require('../../definition/components/version-modal');
// const DagSaveModal = require('./dag-save-modal');
// const ContextMenuItem = require('./dag-context-menu');
// const TaskModal = require('@/views/projects/task/components/node/detail-modal');
// const StartModal = require('@/views/projects/workflow/definition/components/start-modal');
// const LogModal = require('@/components/log-modal');
// const { queryLog } = require('@/service/modules/log');
// const { useAsyncState } = require('@vueuse/core');
// const utils = require('@/utils');
// const { useUISettingStore } = require('@/store/ui-setting/ui-setting');
// const { executeTask } = require('@/service/modules/executors');
// const { removeTaskInstanceCache } = require('@/service/modules/task-instances');

// const props = {
//   // 如果传递了这个prop，就表示从定义详情中传递
//   instance: {
//     type: PropTypes.Object, // 使用Object类型
//     default: undefined
//   },
//   definition: {
//     type: PropTypes.Object, // 使用Object类型
//     default: undefined
//   },
//   readonly: {
//     type: PropTypes.Boolean, // 使用Boolean类型
//     default: false
//   },
//   projectCode: {
//     type: PropTypes.Number, // 使用Number类型
//     default: 0
//   }
// }

// module.exports = defineComponent({
//   name: 'workflow-dag',
//   props,
//   emits: ['refresh', 'save'],
//   setup(props, context) {
//     // ... 代码太长，不再展示完整代码

//     const { t } = useI18n()
//     const route = useRoute()
//     const theme = useThemeStore()

//     const uiSettingStore = useUISettingStore()
//     const logTimer = uiSettingStore.getLogTimer

//     // Whether the graph can be operated
//     provide('readonly', toRef(props, 'readonly'))

//     const graph = ref()
//     provide('graph', graph)
//     context.expose(graph)

//     // Auto layout modal
//     const {
//       visible: layoutVisible,
//       toggle: layoutToggle,
//       formValue,
//       formRef,
//       submit,
//       cancel
//     } = useGraphAutoLayout({ graph })

//     // Edit task
//     const {
//       taskConfirm,
//       taskModalVisible,
//       currTask,
//       taskCancel,
//       appendTask,
//       editTask,
//       copyTask,
//       processDefinition,
//       removeTasks
//     } = useTaskEdit({ graph, definition: toRef(props, 'definition') })

//     // Right click cell
//     const { nodeVariables, menuHide, menuStart, viewLog } = useNodeMenu({
//       graph
//     })

//     // start button in the dag node menu
//     const startDisplay = computed(() => {
//       if (props.definition) {
//         return (
//           route.name === 'workflow-definition-detail' &&
//           props.definition.processDefinition.releaseState === 'ONLINE'
//         )
//       } else {
//         return false
//       }
//     })

//     // execute task buttons in the dag node menu
//     const executeTaskDisplay = computed(() => {
//       return route.name === 'workflow-instance-detail'
//     })

//     // other button in the dag node menu
//     const menuDisplay = computed(() => {
//       if (props.instance) {
//         return (
//           props.instance.state === 'WAITING_THREAD' ||
//           props.instance.state === 'SUCCESS' ||
//           props.instance.state === 'PAUSE' ||
//           props.instance.state === 'FAILURE' ||
//           props.instance.state === 'STOP'
//         )
//       } else if (props.definition) {
//         return props.definition.processDefinition.releaseState === 'OFFLINE'
//       } else {
//         return false
//       }
//     })

//     const taskInstance = computed(() => {
//       if (nodeVariables.menuCell) {
//         const taskCode = Number(nodeVariables.menuCell.id)
//         return taskList.value.find((task) => task.taskCode === taskCode)
//       } else {
//         return undefined
//       }
//     })

//     const currentTaskInstance = ref()

//     watch(
//       () => taskModalVisible.value,
//       () => {
//         if (props.instance && taskModalVisible.value) {
//           const taskCode = currTask.value.code
//           currentTaskInstance.value = taskList.value.find(
//             (task) => task.taskCode === taskCode
//           )
//         }
//       }
//     )

//     const statusTimerRef = ref()
//     const { taskList, refreshTaskStatus } = useNodeStatus({ graph })

//     const { onDragStart, onDrop } = useDagDragAndDrop({
//       graph,
//       readonly: toRef(props, 'readonly'),
//       appendTask
//     })

//     // backfill
//     useGraphBackfill({ graph, definition: toRef(props, 'definition') })

//     // version modal
//     const versionModalShow = ref(false)
//     const versionToggle = (bool) => {
//       if (typeof bool === 'boolean') {
//         versionModalShow.value = bool
//       } else {
//         versionModalShow.value = !versionModalShow.value
//       }
//     }
//     const refreshDetail = () => {
//       context.emit('refresh')
//       versionModalShow.value = false
//     }

//     // Save modal
//     const saveModalShow = ref(false)
//     const saveModelToggle = (bool) => {
//       if (typeof bool === 'boolean') {
//         saveModalShow.value = bool
//       } else {
//         saveModalShow.value = !versionModalShow.value
//       }
//     }
//     const { getConnects, getLocations } = useBusinessMapper()
//     const onSave = (saveForm) => {
//       const edges = graph.value?.getEdges() || []
//       const nodes = graph.value?.getNodes() || []
//       if (!nodes.length) {
//         window.$message.error(t('project.dag.node_not_created'))
//         saveModelToggle(false)
//         return
//       }
//       const connects = getConnects(
//         nodes,
//         edges,
//         processDefinition.value.taskDefinitionList
//       )
//       const locations = getLocations(nodes)
//       context.emit('save', {
//         taskDefinitions: processDefinition.value.taskDefinitionList,
//         saveForm,
//         connects,
//         locations
//       })
//       saveModelToggle(false)
//     }

//     const handleViewLog = (taskId, taskType) => {
//       taskModalVisible.value = false
//       viewLog(taskId, taskType)

//       getLogs(logTimer)
//     }

//     let getLogsID

//     const getLogs = (logTimer) => {
//       const { state } = useAsyncState(
//         queryLog({
//           taskInstanceId: nodeVariables.logTaskId,
//           limit: nodeVariables.limit,
//           skipLineNum: nodeVariables.skipLineNum
//         }).then((res) => {
//           nodeVariables.logRef += res.message || ''
//           if (res && res.message !== '') {
//             nodeVariables.limit += 1000
//             nodeVariables.skipLineNum += res.lineNum
//             getLogs(logTimer)
//           } else {
//             nodeVariables.logLoadingRef = false
//             if (logTimer !== 0) {
//               if (typeof getLogsID === 'number') {
//                 clearTimeout(getLogsID)
//               }
//               getLogsID = setTimeout(() => {
//                 nodeVariables.limit += 1000
//                 nodeVariables.skipLineNum += 1000
//                 getLogs(logTimer)
//               }, logTimer * 1000)
//             }
//           }
//         }),
//         {}
//       )

//       return state
//     }

//     const refreshLogs = (logTimer) => {
//       nodeVariables.logRef = ''
//       nodeVariables.limit = 1000
//       nodeVariables.skipLineNum = 0
//       getLogs(logTimer)
//     }

//     const handleExecuteTask = (startNodeList, taskDependType) => {
//       executeTask(
//         {
//           processInstanceId: Number(route.params.id),
//           startNodeList: startNodeList,
//           taskDependType: taskDependType
//         },
//         props.projectCode
//       ).then(() => {
//         window.$message.success(t('project.workflow.success'))
//         setTimeout(() => {
//           window.location.reload()
//         }, 1000)
//       })
//     }

//     const handleRemoveTaskInstanceCache = (taskId) => {
//       removeTaskInstanceCache(props.projectCode, taskId).then(() => {
//         window.$message.success(t('project.workflow.success'))
//       })
//     }

//     const downloadLogs = () => {
//       utils.downloadFile('log/download-log', {
//         taskInstanceId: nodeVariables.logTaskId
//       })
//     }

//     const onConfirmModal = () => {
//       nodeVariables.showModalRef = false
//     }

//     const layoutSubmit = () => {
//       submit()

//       // Refresh task status in workflow instance
//       if (props.instance) {
//         refreshTaskStatus()
//       }
//     }

//     watch(
//       () => props.definition,
//       () => {
//         if (props.instance) {
//           refreshTaskStatus()
//           statusTimerRef.value = setInterval(() => refreshTaskStatus(), 90000)
//         }
//       }
//     )

//     watch(
//       () => nodeVariables.showModalRef,
//       () => {
//         if (!nodeVariables.showModalRef) {
//           nodeVariables.row = {}
//           nodeVariables.logRef = ''
//           nodeVariables.logLoadingRef = true
//           nodeVariables.skipLineNum = 0
//           nodeVariables.limit = 1000
//         }
//       }
//     )

//     onBeforeUnmount(() => clearInterval(statusTimerRef.value))


//     return {
//       template: `
//         <div
//           :class="[
//             Styles.dag,
//             Styles['dag-' + (theme.darkTheme ? 'dark' : 'light')]
//           ]"
//         >
//           <DagToolbar
//             :layoutToggle="layoutToggle"
//             :instance="props.instance"
//             :definition="props.definition"
//             @versionToggle="versionToggle"
//             @saveModelToggle="saveModelToggle"
//             @removeTasks="removeTasks"
//             @refresh="refreshTaskStatus"
//           />
//           <div :class="Styles.content">
//             <DagSidebar @dragStart="onDragStart" />
//             <DagCanvas @drop="onDrop" />
//           </div>
//           <DagAutoLayoutModal
//             :visible="layoutVisible.value"
//             @submit="layoutSubmit"
//             @cancel="cancel"
//             :formValue="formValue"
//             :formRef="formRef"
//           />
//           <VersionModal
//             v-if="props.definition"
//             :isInstance="!!props.instance"
//             v-model:row="props.definition.processDefinition"
//             v-model:show="versionModalShow.value"
//             @updateList="refreshDetail"
//           />
//           <DagSaveModal
//             v-model:show="saveModalShow.value"
//             @save="onSave"
//             :definition="props.definition"
//             :instance="props.instance"
//           />
//           <TaskModal
//             :readonly="props.readonly"
//             :show="taskModalVisible.value"
//             :projectCode="props.projectCode"
//             :processInstance="props.instance"
//             :taskInstance="currentTaskInstance.value"
//             @viewLog="handleViewLog"
//             :data="currTask.value"
//             :definition="processDefinition"
//             @submit="taskConfirm"
//             @cancel="taskCancel"
//           />
//           <ContextMenuItem
//             :startDisplay="startDisplay.value"
//             :executeTaskDisplay="executeTaskDisplay.value"
//             :menuDisplay="menuDisplay.value"
//             :taskInstance="taskInstance.value"
//             :cell="nodeVariables.menuCell"
//             :visible="nodeVariables.menuVisible"
//             :left="nodeVariables.pageX"
//             :top="nodeVariables.pageY"
//             @hide="menuHide"
//             @start="menuStart"
//             @edit="editTask"
//             @copyTask="copyTask"
//             @removeTasks="removeTasks"
//             @viewLog="handleViewLog"
//             @executeTask="handleExecuteTask"
//             @removeTaskInstanceCache="handleRemoveTaskInstanceCache"
//           />
//           <StartModal
//             v-if="props.definition"
//             v-model:row="props.definition.processDefinition"
//             v-model:show="nodeVariables.startModalShow"
//             :taskCode="nodeVariables.taskCode"
//           />
//           <LogModal
//             v-if="props.instance"
//             :showModalRef="nodeVariables.showModalRef"
//             :logRef="nodeVariables.logRef"
//             :row="nodeVariables.row"
//             :showDownloadLog="true"
//             :logLoadingRef="nodeVariables.logLoadingRef"
//             @confirmModal="onConfirmModal"
//             @refreshLogs="refreshLogs"
//             @downloadLogs="downloadLogs"
//           />
//         </div>
//       `,
//       // 其他组件选项，例如 data、methods、computed 等等
//     }

//   }
// });

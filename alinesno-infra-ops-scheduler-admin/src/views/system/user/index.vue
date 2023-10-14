<template>
   <div class="app">
      <div class="app-stencil" ref="stencilContainer"></div>
      <div class="app-content" ref="container"></div>
   </div>
</template>
 
<script setup>
import { ref, onMounted } from 'vue';
import { Graph, Addon, Shape } from '@antv/x6';

const { Stencil } = Addon;
const { Rect, Ellipse } = Shape;

// 不同节点类型的icon
const NODE_TYPE_LOGO = {
  INPUT: 'https://mdn.alipayobjects.com/huamei_f4t1bn/afts/img/A*RXnuTpQ22xkAAAAAAAAAAAAADtOHAQ/original', // 数据输入
  FILTER: 'https://mdn.alipayobjects.com/huamei_f4t1bn/afts/img/A*ZJ6qToit8P4AAAAAAAAAAAAADtOHAQ/original', // 数据筛选
  JOIN: 'https://mdn.alipayobjects.com/huamei_f4t1bn/afts/img/A*EHqyQoDeBvIAAAAAAAAAAAAADtOHAQ/original', // 数据连接
  UNION:'https://mdn.alipayobjects.com/huamei_f4t1bn/afts/img/A*k4eyRaXv8gsAAAAAAAAAAAAADtOHAQ/original', // 数据合并
  AGG: 'https://mdn.alipayobjects.com/huamei_f4t1bn/afts/img/A*TKG8R6nfYiAAAAAAAAAAAAAADtOHAQ/original', // 数据聚合
  OUTPUT:'https://mdn.alipayobjects.com/huamei_f4t1bn/afts/img/A*zUgORbGg1HIAAAAAAAAAAAAADtOHAQ/original', // 数据输出
}

const container = ref(null);
const stencilContainer = ref(null);

onMounted(() => {
   const graph = new Graph({
      container: container.value,
      grid: true,
      snapline: {
         enabled: true,
         sharp: true,
      },
      resizing: {
         enabled: false,
      },
      scroller: {
         enabled: true,
         pageVisible: false,
         pageBreak: false,
         pannable: true,
      },
      mousewheel: {
         enabled: true,
         modifiers: ['ctrl', 'meta'],
      },
   });

   graph.on('node:delete', ({ view, e }) => {
      e.stopPropagation()
      view.cell.remove()
   })

   graph.on('node:customevent', ({ name, view, e }) => {
      if (name === 'node:delete') {
         e.stopPropagation()
         view.cell.remove()
      }
   })


   const source = graph.addNode({
      x: 130,
      y: 30,
      width: 100,
      height: 40,
      attrs: {
         label: {
            text: 'Hello',
            fill: '#6a6c8a',
         },
         body: {
            stroke: '#FFF',
         },
         // 表示一个删除按钮，点击时删除该节点
         image: {
            event: 'node:delete',
            xlinkHref: 'trash.png',
            width: 20,
            height: 20,
         },
      },
   });

   const target = graph.addNode({
      x: 320,
      y: 240,
      width: 100,
      height: 40,
      attrs: {
         label: {
            text: 'World',
            fill: '#6a6c8a',
         },
         body: {
            stroke: '#FFF',
         },
      },
   });

   graph.addEdge({ source, target });

   graph.centerContent();

   const stencil = new Stencil({
      title: '工具栏',
      target: graph,
      // search(cell, keyword) {
      //    return cell.shape.indexOf(keyword) !== -1;
      // },
      // placeholder: '搜索工具栏',
      // notFoundText: '无此任务节点',
      collapsable: true,
      layoutOptions: {
         columns: 1,
         dx: 10,
         rowHeight: 50,
         marginX: 45,
         center: true,
      },
      stencilGraphWidth: 200,
      stencilGraphHeight: 600,
      attrs: {

      },
      groups: [
         {
            name: 'group1',
            title: '任务节点列表',
         },
      ],
   });

   stencilContainer.value.appendChild(stencil.container);

   const r = new Rect({
      width: 180,
      height: 40,
      attrs: {
         rect: { fill: '#FFF', stroke: '#4B4A67', strokeWidth: 1 , strokeDasharray: '3 3',  rx: 3, ry: 3, },
         text: { text: 'SPARK', fill: '#222' },
         label: {
      fontSize: 14,
      fill: '#333333',
      refX: '50%',
      refY: '50%',
      textAnchor: 'middle',
      textVerticalAnchor: 'middle',
    },
         html: {
               html: '<b>hello</b>', // FontAwesome图标的HTML代码
               width: 20,
               height: 20,
               x: 10,
               y: 7.5,
            },
      },
      style: {
         borderRadius: 10,
      },
   });

   const c = new Rect({
      width: 180,
      height: 40,
      attrs: {
         rect: { fill: '#FFF', stroke: '#4B4A67', strokeWidth: 1 , strokeDasharray: '3 3',  rx: 3, ry: 3, },
         text: { text: 'FLINK', fill: '#222' },
      },
   });

   const c2 = new Rect({
      width: 180,
      height: 40,
      attrs: {
         rect: { fill: '#FFF', stroke: '#4B4A67', strokeWidth: 1 , strokeDasharray: '3 3',  rx: 3, ry: 3, },
         text: { text: 'SHELL', fill: '#222' },
      },
   });

   const r2 = new Rect({
      width: 180,
      height: 40,
      attrs: {
         rect: { fill: '#FFF', stroke: '#4B4A67', strokeWidth: 1 , strokeDasharray: '3 3',  rx: 3, ry: 3, },
         text: { text: 'SQL', fill: '#222' },
      },
   });

   const r3 = new Rect({
      width: 180,
      height: 40,
      attrs: {
         rect: { fill: '#FFF', stroke: '#4B4A67', strokeWidth: 1 , strokeDasharray: '3 3',  rx: 3, ry: 3, },
         text: { text: 'HTTP', fill: '#222' },
      },
   });

   const r4 = new Rect({
      width: 180,
      height: 40,
      attrs: {
         rect: { fill: '#FFF', stroke: '#4B4A67', strokeWidth: 1 , strokeDasharray: '3 3',  rx: 3, ry: 3, },
         text: { text: 'PYTHON', fill: '#222' },
      },
   });

   const r5 = new Rect({
      width: 180,
      height: 40,
      attrs: {
         rect: { fill: '#FFF', stroke: '#4B4A67', strokeWidth: 1 , strokeDasharray: '3 3',  rx: 3, ry: 3, },
         text: { text: 'PROCEDURE', fill: '#222' },
      },
   });

   const r6 = new Rect({
      width: 180,
      height: 40,
      attrs: {
         rect: { fill: '#FFF', stroke: '#4B4A67', strokeWidth: 1 , strokeDasharray: '3 3',  rx: 3, ry: 3, },
         text: { text: 'MAVEN', fill: '#222' },
      },
   });

   const r7 = new Rect({
      width: 180,
      height: 40,
      attrs: {
         rect: { fill: '#FFF', stroke: '#4B4A67', strokeWidth: 1 , strokeDasharray: '3 3',  rx: 3, ry: 3, },
         text: { text: 'GIT', fill: '#222' },
      },
   });

   const r8 = new Rect({
      width: 180,
      height: 40,
      attrs: {
         rect: { fill: '#FFF', stroke: '#4B4A67', strokeWidth: 1 , strokeDasharray: '3 3',  rx: 3, ry: 3, },
         text: { text: 'GROOVY', fill: '#222', fontSize: 13 },
      },
   });

   stencil.load([r, c, c2, r3, r4, r5, r6, r7, r8], 'group1');
});

</script>
 
<style lang="scss">
.app {
   display: flex;
   padding: 10px;
   height: 80vh;
}

.app-stencil {
   flex: 0 0 200px;
   border-right: 0px solid #ccc;
   background-color: #fff;
   position: relative;

   .x6-graph-background {
      background-color: #fff;
   }

   .x6-widget-stencil-group-title,
   .x6-widget-stencil-title {
      background: #fff;
   }

   .x6-widget-stencil {
      background-color: #fff;
   }
}

.app-content {
   flex: 1;
}
</style>
 
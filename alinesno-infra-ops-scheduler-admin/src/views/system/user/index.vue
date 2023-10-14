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
const { Rect, Circle } = Shape;

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
         enabled: false ,
      },
      scroller: {
         enabled: true,
         pageVisible: false,
         pageBreak: false,
         pannable: true,
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
            stroke: '#31d0c6',
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
            stroke: '#31d0c6',
         },
      },
   });

   graph.addEdge({ source, target });

   graph.centerContent();

   const stencil = new Stencil({
      title: '工具栏',
      target: graph,
      search(cell, keyword) {
         return cell.shape.indexOf(keyword) !== -1;
      },
      placeholder: '搜索工具栏',
      notFoundText: '无此任务节点',
      collapsable: true,
      layoutOptions: {
         columns: 1 ,
         dx: 10 ,
         rowHeight: 50 ,
         marginX: 45 , 
         center: true , 
      } , 
      stencilGraphWidth: 200,
      stencilGraphHeight: 600,
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
         rect: { fill: '#31D0C6', stroke: '#4B4A67', strokeWidth: 1 },
         text: { text: 'rect', fill: 'white' },
      },
   });

   const c = new Rect({
      width: 180,
      height: 40,
      attrs: {
         rect: { fill: '#31D0C6', stroke: '#4B4A67', strokeWidth: 1 },
         text: { text: 'ellipse', fill: 'white' },
      },
   });

   const c2 = new Rect({
      width: 180,
      height: 40,
      attrs: {
         rect: { fill: '#31D0C6', stroke: '#4B4A67', strokeWidth: 1 },
         text: { text: 'ellipse', fill: 'white' },
      },
   });

   const r2 = new Rect({
      width: 180,
      height: 40,
      attrs: {
         rect: { fill: '#31D0C6', stroke: '#4B4A67', strokeWidth: 1 },
         text: { text: 'ellipse', fill: 'white' },
      },
   });

   stencil.load([r, c, c.clone() , c2, r2.clone()], 'group1');
   // stencil.load([c2.clone(), r2, r3, c3], 'group2');
});

</script>
 
<style scoped lang="scss">
.app {
   display: flex;
   height: 80vh;
}

.app-stencil {
   flex: 0 0 200px;
   border-right: 1px solid #ccc;
   position: relative;
}

.app-content {
   flex: 1;
}
</style>
 
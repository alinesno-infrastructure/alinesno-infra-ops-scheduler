<template>
  <div>
    this is a test {{ percentage }}
    <el-progress type="dashboard" :percentage="percentage" :color="colors" />
  </div>
</template>

<script setup>
import { inject, ref, onMounted } from 'vue';

const getNode = inject('getNode');
const percentage = ref(80);

const colors = [
  { color: '#f56c6c', percentage: 20 },
  { color: '#e6a23c', percentage: 40 },
  { color: '#5cb87a', percentage: 60 },
  { color: '#1989fa', percentage: 80 },
  { color: '#6f7ad3', percentage: 100 },
]

onMounted(() => {
  const node = getNode();
  console.log(node);
});

</script>

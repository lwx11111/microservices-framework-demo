<template>
    <router-view v-slot="{ Component }" :key="key">
        <keep-alive>
            <component :is="Component" />
        </keep-alive>
    </router-view>
</template>

<script lang="ts" setup>
import { computed } from 'vue'
import { useStore } from "vuex";
import { useRouter } from 'vue-router'

const store = useStore();
const router = useRouter();

const key = computed(() => {
    return router
})
</script>

<style lang="scss" scoped>
.app-main {
  min-height: calc(100vh - 50px);
  width: 100%;
  position: relative;
  overflow: hidden;
}

.fixed-header+.app-main {
  padding-top: 50px;
}

.hasTagsView {
  .app-main {
    min-height: calc(100vh - 84px);
  }

  .fixed-header+.app-main {
    padding-top: 84px;
  }
}
</style>

<style lang="scss">
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>

/* —————————————— Vant 组件注册 (需使用this方法的特殊组件): uniapp版 ———————————————— */
import Vue from 'vue'

import Dialog from '@vant/weapp/dialog/dialog';
import Toast from '@vant/weapp/toast/toast';
import Notify from '@vant/weapp/notify/notify';

Vue.prototype.$dialog = Dialog;
Vue.prototype.$toast = Toast;
Vue.prototype.$notify = Notify;

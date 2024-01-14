<template>
  <div>
    <div
      style=" height: 60px; background-color: #fff; display: flex; align-items: center; border-bottom: 1px solid #ddd ;justify-content: space-between">
      <div>
        <div style="padding-left: 20px; display: flex; align-items: center">
          <div style="font-weight: bold; font-size: 24px; margin-left: 5px">小区物业管理系统</div>
        </div>
      </div>
      <div style=" padding-right: 10px; display: flex; align-items: center;">
        <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" alt=""
          style="width: 40px; height: 40px ;padding-right:10px;">

        <el-dropdown :hide-on-click="false" style="font-size: large;">
          <span class="el-dropdown-link">{{ data.username }} ({{ data.type }}
            <span v-if="data.type == '住户'">{{ data.name }}</span>
            )
            <el-icon class="el-icon--right"><arrow-down /></el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>

      </div>
    </div>

    <div style="display: flex">
      <div style="width: 200px; border-right: 1px solid #ddd; min-height: calc(100vh - 65px)">
        <el-menu router style="border: none">
          <el-menu-item index="/home">
            <el-icon>
              <HomeFilled />
            </el-icon>
            <span>系统首页</span>
          </el-menu-item>
          <el-sub-menu index="" v-if="data.type == '管理员'">
            <template #title >
              <el-icon>
                <Money />
              </el-icon>
              <span>收费管理</span>
            </template>
            <el-menu-item index="/charge-detail" >
              <el-icon>
                <List />
              </el-icon>
              <span>收费明细</span>
            </el-menu-item>
            <el-menu-item index="/charge-item" >
              <el-icon>
                <Files />
              </el-icon>
              <span>收费项目管理</span>
            </el-menu-item>
            <el-menu-item index="/pre-payment" >
              <el-icon>
                <Ticket />
              </el-icon>
              <span>预付款管理</span>
            </el-menu-item>
          </el-sub-menu>
          <el-menu-item index="/complaint" v-if="data.type == '管理员'">
            <el-icon>
              <ChatLineSquare />
            </el-icon>
            <span>住户投诉管理</span>
          </el-menu-item>
          <el-menu-item index="/repair" v-if="data.type == '管理员'">
            <el-icon>
              <SuitcaseLine />
            </el-icon>
            <span>小区维修管理</span>
          </el-menu-item>
          <el-menu-item index="/parking" v-if="data.type == '管理员'">
            <el-icon>
              <Van />
            </el-icon>
            <span>小区车位管理</span>
          </el-menu-item>
          <el-menu-item index="/owner" v-if="data.type == '管理员'">
            <el-icon>
              <User />
            </el-icon>
            <span>住户管理</span>
          </el-menu-item>
          <el-menu-item index="/ziliao" v-if="data.type == '管理员'">
            <el-icon>
              <Tickets />
            </el-icon>
            <span>小区信息</span>
          </el-menu-item>
          <el-menu-item index="/meter" v-if="data.type == '管理员'">
            <el-icon>
              <Clock />
            </el-icon>
            <span>计量仪表管理</span>
          </el-menu-item>



          <el-menu-item index="/personnel-complaint" v-if="data.type == '物业人员'">
            <el-icon>
              <Clock />
            </el-icon>
            <span>投诉处理</span>
          </el-menu-item>

          <el-menu-item index="/personnel" v-if="data.type == '物业人员'">
            <el-icon>
              <Clock />
            </el-icon>
            <span>报修处理</span>
          </el-menu-item>

          <el-menu-item index="/personnel-meter" v-if="data.type == '物业人员'">
            <el-icon>
              <Clock />
            </el-icon>
            <span>抄表处理</span>
          </el-menu-item>






          <el-menu-item index="/pay" v-if="data.type == '住户'" >
            <el-icon>
              <Money />
            </el-icon>
            <span>账户充值</span>
          </el-menu-item>

          <el-menu-item index="/my-repair" v-if="data.type == '住户'" >
            <el-icon>
              <Money />
            </el-icon>
            <span>我的报修</span>
          </el-menu-item>

          <el-menu-item index="/owner-complain" v-if="data.type == '住户'" >
            <el-icon>
              <Money />
            </el-icon>
            <span>我的投诉</span>
          </el-menu-item>


          <el-menu-item index="/login" @click="logout">
            <el-icon>
              <SwitchButton />
            </el-icon>
            <span>退出系统</span>
          </el-menu-item>
        </el-menu>
      </div>

      <div style="width: 100%; background-color: #f8f8ff; padding: 10px;">
        <el-card shadow="always" style="min-height: 90vh;">
          <router-view />
        </el-card>
      </div>
    </div>

  </div>
</template>

<script setup>
import { reactive } from 'vue';

const data = reactive({
  username: '',
  type: '',
  name:'',
})

data.username = localStorage.getItem("username");
data.type = localStorage.getItem("type");
if(data.type === '住户'){
  data.name = localStorage.getItem("name");
}

function logout() {
  localStorage.removeItem('token');
  localStorage.removeItem('username');
  localStorage.removeItem('type');
  console.log('logout');
}

</script>
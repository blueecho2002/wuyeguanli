<template>
  <div id="card-container">
    <el-card shadow="hover">
      <el-form :model="data.form" style="width: 350px;">
        <div style="text-align: center;font-size: 1.5em; margin-bottom: 20px;">登 录</div>
        <el-input prefix-icon="User" v-model="data.form.username" placeholder="请输入账号" style="margin-bottom: 15px;" />
        <el-input prefix-icon="Lock" v-model="data.form.password" placeholder="请输入密码" show-password
          style="margin-bottom: 20px;" />
        <div id="button-div">
          <el-button type="primary" plain>注册</el-button>
          <el-button @click="login" type="primary" style="">登录</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive } from 'vue';
import { inject } from 'vue';

const axios = inject('$axios')

const data = reactive({
  form: {
    username: '',
    password: '',
  }
})

function login() {
  axios({
    method: 'get',
    url: 'http://localhost:8081/admin-login',
    params: {
      username: data.form.username,
      password: data.form.password,
    }
  }).then(function (response) {
    if(response.data.data == null){
      alert("账号或密码错误")
    }else{
      alert("登录成功")
    }
    console.log(response);
  }).catch(function (error) {
    console.log(error);
  });

}


</script>

<style scoped>
#card-container {
  min-height: 100vb;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url("assets/img/login_bg.jpg");
  background-size: cover;
}

#button-div {
  display: flex;
  justify-content: right;
}
</style>

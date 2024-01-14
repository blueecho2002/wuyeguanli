<template>
  <div id="card-container">
    <el-card shadow="hover">
      <el-form :model="data.form" style="width: 350px;">
        <div style="text-align: center;font-size: 1.5em; margin-bottom: 20px;">登 录</div>
        <el-input prefix-icon="User" v-model="data.form.username" placeholder="请输入账号" style="margin-bottom: 15px;" />
        <el-input prefix-icon="Lock" v-model="data.form.password" placeholder="请输入密码" show-password
          style="margin-bottom: 20px;" />
        <div id="button-div">
          <el-button @click="data.registerDialogFormVisible = true" type="primary" plain>注册</el-button>
          <el-button @click="login" type="primary" style="">登录</el-button>
        </div>
      </el-form>
    </el-card>
  </div>

  <el-dialog v-model="data.registerDialogFormVisible" title="业主注册">
    <el-form :model="registerForm" :rules="rules" ref="registerFormRef">
      <el-form-item label="身份证号" :label-width="formLabelWidth" prop="idCard">
        <el-input v-model="registerForm.idCard" autocomplete="off" placeholder="请输入身份证号" />
      </el-form-item>
      <el-form-item label="账号" :label-width="formLabelWidth" prop="username">
        <el-input v-model="registerForm.username" autocomplete="off" placeholder="请输入账号" />
      </el-form-item>
      <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
        <el-input v-model="registerForm.password" autocomplete="off" placeholder="请输入密码" />
      </el-form-item>
      <el-form-item label="确认密码" :label-width="formLabelWidth" prop="passwordAgain">
        <el-input v-model="registerForm.passwordAgain" autocomplete="off" placeholder="请再次输入密码" />
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.registerDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="register()">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>
  
<script setup>
import { reactive } from 'vue';
import { inject } from 'vue';
import { ElMessage } from 'element-plus';
import { jwtDecode } from "jwt-decode";


const axios = inject('$axios')

const registerForm = reactive({
  idCard:'',
  username: '',
  password: '',
  passwordAgain: ''

})

const data = reactive({
  registerDialogFormVisible: false,
  form: {
    username: '',
    password: '',
  }
})

const formLabelWidth = "140px"

function login() {
  axios({
    method: 'get',
    url: 'http://localhost:8081/login',
    params: {
      username: data.form.username,
      password: data.form.password,
    }
  }).then(function (response) {
    if (response.data.code == '0') {
      ElMessage({
        message: response.data.msg,
        type: 'error',
      })
    } else {
      ElMessage({
        message: '登录成功',
        type: 'success',
      })

      console.log(response.data.data);
      localStorage.setItem('token', response.data.data);
      localStorage.setItem('username', jwtDecode(response.data.data).username);
      localStorage.setItem('type', jwtDecode(response.data.data).type);
      if(jwtDecode(response.data.data).type == '住户'){
        localStorage.setItem('name', jwtDecode(response.data.data).name);
      }
      location.href = '/home';
    }
    console.log(response);
  }).catch(function (error) {
    console.log(error);
  });

}

function register() {
  if (registerForm.password != registerForm.passwordAgain) {
    ElMessage({
      type: 'error',
      message: '两次密码输入不一致!',
    })
  } else {

    axios({
      method: 'get',
      url: 'http://localhost:8081/register',
      params: {
        idCard: registerForm.idCard,
        username: registerForm.username,
        password: registerForm.password,
      }
    }).then(function (response) {
      if (response.data.code == '0') {
        ElMessage({
          message: response.data.msg,
          type: 'error',
        })
      } else {
        ElMessage({
          message: '注册成功',
          type: 'success',
        })

      }
      console.log(response);
    }).catch(function (error) {
      console.log(error);
    });
  }
}

const rules = reactive({
  idCard: [
    { required: true, message: '请输入身份证号', trigger: 'change' },
  ],
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
  ],
  passwordAgain: [
    { required: true, message: '请确认密码', trigger: 'blur' },
  ]
})
</script>
  
<style scoped>
#card-container {
  height: 100vb;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url("../assets/img/login_bg.jpg");
  background-size: cover;
}

#button-div {
  display: flex;
  justify-content: right;
}
</style>
  
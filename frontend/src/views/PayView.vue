<template>
  <el-form style=" margin-left: 0;">

      <el-form-item label="账户余额" style="margin-top: 20px; ">
        <el-input v-model="data.balance" placeholder="请输入充值金额" style="width: 210px;" readonly />
      </el-form-item>
      <el-form-item label="充值金额" style="margin-top: 20px; ">
        <el-input v-model="data.money" placeholder="请输入充值金额" style="width: 210px;" />
      </el-form-item>

    <el-button @click="pay()" type="primary"
      style="margin-left: 50px; width: 100px; margin-left: 0;">充值</el-button>

  </el-form>
</template>


<script setup>
import { reactive } from 'vue';
import { ElMessageBox } from 'element-plus';
import { inject } from 'vue';
import { ElMessage } from 'element-plus';
const axios = inject('$axios')

const data = reactive({
  money:0,
  timestamp:'',
  balance:0,
})

function pay(){
  data.timestamp = Date.now();
  window.open("http://localhost:8081/alipay/pay?subject=账户充值&TradeNo="+ data.timestamp +"&totalAmount="+data.money)
  ElMessageBox.confirm(
    '已完成支付?',
    'info',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(() => {
    paycheck();
  }).catch(()=>{
    paycheck();
  })
}

function paycheck(){
  axios({
    method: 'get',
    url: 'http://localhost:8081/getPayFeedback',
    params: {
      id:data.timestamp,
      username:localStorage.getItem("username"),
    }
  }).then(function (response) {
    if (response.data.code == 1) {
      ElMessage({
        type: 'success',
        message: '充值成功！充值金额：' + data.money,
      })
      getBalance();
    } else {
      ElMessage({
        type: 'error',
        message: '充值失败！',
      })
    }
  }).catch(function (error) {

  });
}

getBalance();

function getBalance(){
  axios({
    method: 'get',
    url: 'http://localhost:8081/getBalance',
    params: {
      username:localStorage.getItem("username"),
    }
  }).then(function (response) {
    if(response.data.code == '1')
      data.balance = response.data.data;
  }).catch(function (error) {

  });
}

</script>
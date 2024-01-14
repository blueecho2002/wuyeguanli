<template>
  <div class="class">
      <el-form style=" margin-left: 0;">
        <el-row>
          <el-form-item label="收费项目" style="margin-top: 20px;">
            <el-input v-model="data.chargeItemName" placeholder="请输入收费项目" style="width: 140px;" clearable />
          </el-form-item>
          <el-form-item label="收费项目ID" style="margin-top: 20px; margin-left: 30px;">
            <el-input v-model="data.chargeItemId" placeholder="请输入收费项目ID" style="width: 140px;" clearable />
          </el-form-item>
          <el-form-item label="创建时间" style="margin-top: 20px; margin-left: 30px;">
            <el-date-picker v-model="data.date" type="datetimerange" start-placeholder="Start Date"
              end-placeholder="End Date" :default-time="defaultTime" value-format="YYYY-MM-DD HH:m:s" />
            <el-button type="primary" style="margin-left: 30px; width: 100px;" @click="getChargeItem">查询</el-button>
            <el-button type="primary" style="margin-left: 10px; width: 100px;" @click="reset">重置</el-button>
          </el-form-item>

        </el-row>
        <el-button @click="data.newItemDialogFormVisible = true" type="primary"
          style="margin-left: 50px; width: 100px; margin-left: 0;">新建收费项目</el-button>

        <el-button @click="deleteSelectedRows" type="danger"
          style="margin-left: 50px; width: 100px; margin-left: 10px;">批量删除</el-button>
      </el-form>
      <el-table :data="data.tableData" style="margin-top: 30px;width: 1410px;" stripe ref="tableRef">
        <el-table-column type="selection" width="50" />
        <el-table-column prop="id" label="项目ID" width="120" />
        <el-table-column prop="name" label="项目名称" width="200" />
        <el-table-column prop="money" label="费用(元)" width="120" />
        <el-table-column prop="unit" label="单位" width="120" />
        <el-table-column prop="period" label="收费周期" width="120" />
        <el-table-column prop="updateTime" label="创建时间" width="180" />
        <el-table-column prop="updateTime" label="更新时间" width="180" />
        <el-table-column fixed="right" label="操作" width="200">
          <template #default="scope">
            <el-button link type="primary" @click="editItemFormLoad(scope.row)">
              编辑
            </el-button>
            <el-button link type="danger" @click="deleteSingle(scope.row.id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination style="margin-top: 30px;" v-model:current-page="data.currentPage" v-model:page-size="data.pageSize"
        :page-sizes="[10, 20, 50, 100]" layout="total, sizes, prev, pager, next, jumper" :total="data.totalItem"
        @size-change="getChargeItem()" @current-change="getChargeItem()" />
  </div>

  <!-- 修改收费项目对话框 -->
  <el-dialog v-model="data.editItemDialogFormVisible" title="修改收费项目">
    <el-form :model="editItemForm" :rules="rules" ref="editItemFormRef">
      <el-form-item label="项目名称" :label-width="formLabelWidth" prop="name">
        <el-input v-model="editItemForm.name" autocomplete="off" placeholder="请输入项目名称" />
      </el-form-item>
      <el-form-item label="费用（元）" :label-width="formLabelWidth" prop="money">
        <el-input v-model="editItemForm.money" autocomplete="off" placeholder="请输入项目费用" />
      </el-form-item>
      <el-form-item label="单位" :label-width="formLabelWidth" prop="unit">
        <el-input v-model="editItemForm.unit" autocomplete="off" placeholder="请输入单位，如：/平方米" />
      </el-form-item>
      <el-form-item label="收费周期" :label-width="formLabelWidth" prop="period">
        <el-input v-model="editItemForm.period" autocomplete="off" placeholder="请输入收费周期，如：每年" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.editItemDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="editItem()">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 新增收费项目对话框 -->
  <el-dialog v-model="data.newItemDialogFormVisible" title="新建收费项目">
    <el-form :model="newChargeItemForm" :rules="rules" ref="newChargeItemFormRef">
      <el-form-item label="项目名称" :label-width="formLabelWidth" prop="name">
        <el-input v-model="newChargeItemForm.name" autocomplete="off" placeholder="请输入项目名称" />
      </el-form-item>
      <el-form-item label="费用（元）" :label-width="formLabelWidth" prop="money">
        <el-input v-model="newChargeItemForm.money" autocomplete="off" placeholder="请输入项目费用" />
      </el-form-item>
      <el-form-item label="单位" :label-width="formLabelWidth" prop="unit">
        <el-input v-model="newChargeItemForm.unit" autocomplete="off" placeholder="请输入单位，如：/平方米" />
      </el-form-item>
      <el-form-item label="收费周期" :label-width="formLabelWidth" prop="period">
        <el-input v-model="newChargeItemForm.period" autocomplete="off" placeholder="请输入收费周期，如：每年" />
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.newItemDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="addChargeItem()">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { reactive, ref } from 'vue';
import { ElMessageBox } from 'element-plus'
import { inject } from 'vue';
import { ElMessage } from 'element-plus';
const axios = inject('$axios')

const formLabelWidth = '140px'

const rules = reactive({
  money: [
    { required: true, message: '请输入费用', trigger: 'change' },

  ],
  unit: [
    //{ required: true, message: '请输入付款人', trigger: 'blur' },
  ],
  period: [
    { required: true, message: '请输入应收金额', trigger: 'blur' },
  ],
  name: [
    { required: true, message: '请输入收费项目名称', trigger: 'blur' },
  ]
})


const editItemForm = reactive({
  name: '',
  unit: '',
  period: '',
  money: 0,
})

const newChargeItemForm = reactive({
  name: '',
  unit: '',
  period: '',
  money: 0,
})


const data = reactive({
  hostname: 'localhost',
  totalItem: 0,
  currentPage: 1,
  pageSize: 10,

  date: '',//日期选择器日期
  chargeItemId: '',
  chargeItemName:'',

  editItemDialogFormVisible: false,
  newItemDialogFormVisible: false,

  tableData: [],
  options: [],
})

const newChargeItemFormRef = ref();
const editItemFormRef = ref();
const tableRef = ref();

function deleteSingle(id){
  ElMessageBox.confirm(
    '您确定要删除该收费项目吗?',
    'Warning',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(() => {
    deleteChargeItem(id)
  }).catch(()=>{})
}

function deleteSelectedRows() {
  ElMessageBox.confirm(
    '您确定要删除该收费项目吗?',
    'Warning',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(() => {
    const selectedRows = tableRef.value.getSelectionRows()
    console.log(selectedRows);
    selectedRows.forEach(element => {
      deleteChargeItem(element.id);
    });
  }).catch(()=>{})

}

function editItemFormLoad(row) {
  console.log(row);
  editItemForm.money = row.money;
  editItemForm.name = row.name;
  editItemForm.period = row.period;
  editItemForm.unit = row.unit;
  data.editItemDialogFormVisible = true;
}

function reset() {
  data.chargeItemName = '';
  data.ownerId = '';
  data.owner = '';
  data.date = '';
  getAllChargeItem();
  getChargeItem();
}

function addChargeItem() {
  newChargeItemFormRef.value.validate((valid) => {
    if (valid) {
      axios({
        method: 'post',
        url: 'http://' + data.hostname + ':8081/addChargeItem',
        data: {
          name: newChargeItemForm.name,
          money: newChargeItemForm.money,
          period: newChargeItemForm.period,
          unit: newChargeItemForm.unit,
        }
      }).then(function (response) {
        if (response.data.code == 1) {
          ElMessage({
            type: 'success',
            message: '添加成功',
          })
          getChargeItem()
        } else {
          ElMessage({
            type: 'error',
            message: '添加失败',
          })
        }
        console.log(response);
      }).catch(function (error) {
        console.log(error);
      })
      data.newItemDialogFormVisible = false
    } else {
      ElMessage({
        type: 'error',
        message: '添加失败',
      })
    }
  })


}

function editItem() {
  editItemFormRef.value.validate((valid) => {
    if (valid) {
      axios({
        method: 'post',
        url: 'http://' + data.hostname + ':8081/editChargeItem',
        data: {
          name:editItemForm.name,
          period:editItemForm.period,
          money:editItemForm.money,
          unit:editItemForm.unit,
        }
      }).then(function (response) {
        if (response.data.code == 1) {
          ElMessage({
            type: 'success',
            message: '修改成功',
          })
          getChargeItem()
        } else {
          ElMessage({
            type: 'error',
            message: response.data.msg,
          })
        }
        console.log(response);
      }).catch(function (error) {
        console.log(error);
      })
      data.editItemDialogFormVisible = false;
    } else {
      ElMessage({
        type: 'error',
        message: '修改失败',
      })
    }
  })


}

function deleteChargeItem(id) {
    axios({
      method: 'delete',
      url: 'http://' + data.hostname + ':8081/deleteChargeItem/' + id,
    }).then(function (response) {
      if (response.data.code == 1) {
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
        getChargeItem()
      } else {
        ElMessage({
          type: 'error',
          message: '删除失败',
        })
      }
      console.log(response);
    }).catch(function (error) {
      console.log(error);
    });
}

function getChargeItem() {
  axios({
    method: 'get',
    url: 'http://'+data.hostname+':8081/getChargeItem',
    params: {
      page: data.currentPage,
      pageSize: data.pageSize,
      chargeItemName: data.chargeItemName,
      chargeItemId: data.chargeItemId,
      from_time: data.date == null ? null : data.date[0],
      to_time: data.date == null ? null : data.date[1],
    }
  }).then(function (response) {
    if (response.data.code == 1) {
      data.totalItem = response.data.data.total
      data.tableData = response.data.data.rows;
    } else {
      ElMessage({
        type: 'error',
        message: '登录过期，请重新登录',
      })
    }
  }).catch(function (error) {
    console.log(localStorage.getItem('token'));
    console.log(error);
  });
}

function getAllChargeItem() {
  axios({
    method: 'get',
    url: 'http://'+data.hostname+':8081/getAllChargeItem',
  }).then(function (response) {
    data.options = response.data.data;
  }).catch(function (error) {
    console.log(localStorage.getItem('token'));
    console.log(error);
  });
}

getChargeItem();
getAllChargeItem();

const defaultTime = [
  new Date(2000, 1, 1, 0, 0, 0),
  new Date(2000, 2, 1, 0, 0, 0),
]

</script>

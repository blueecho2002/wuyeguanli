<template>
  <div class="class">
    <el-card shadow="always">
      <el-form style=" margin-left: 0;">
        <el-row>
          <el-form-item label="班级名称" style="margin-top: 20px; ">
            <el-input v-model="data.classname" placeholder="请输入班级名称" style="width: 210px;" />
          </el-form-item>
          <el-form-item label="结课时间" style="margin-top: 20px; margin-left: 30px;">
            <div class="demo-date-picker">
              <div class="block">
                <el-date-picker v-model="data.date1" type="daterange" range-separator="To" start-placeholder="Start date"
                  end-placeholder="End date" format="YYYY/MM/DD" value-format="YYYY-MM-DD" unlink-panels />
              </div>
            </div>
            <el-button type="primary" style="margin-left: 30px; width: 100px;" @click="getAllClass">查询</el-button>
          </el-form-item>
        </el-row>
        <el-button @click="data.newClassDialogFormVisible = true" type="primary"
          style="margin-left: 50px; width: 100px; margin-left: 0;">新增班级</el-button>

      </el-form>
      <el-table :data="data.tableData" style="margin-top: 30px;width: 1000px;" stripe>
        <el-table-column prop="id" label="序号" width="120" />
        <el-table-column prop="name" label="班级名称" width="200" />
        <el-table-column prop="classroom" label="班级教室" width="120" />
        <el-table-column prop="from_time" label="开课时间" width="120" />
        <el-table-column prop="to_time" label="结课时间" width="120" />
        <el-table-column prop="teacher" label="班主任" width="120" />
        <el-table-column fixed="right" label="操作" width="200">
          <template #default="scope">
            <el-button link type="primary" @click="editClassFormLoad(scope.row)">
              编辑
            </el-button>
            <el-button link type="primary" @click="deleteClass(scope.row.id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination style="margin-top: 30px;" v-model:current-page="data.currentPage4" v-model:page-size="data.pageSize4"
        :page-sizes="[10, 20, 50, 100]" layout="total, sizes, prev, pager, next, jumper" :total="data.totalItem"
        @size-change="getAllClass()" @current-change="getAllClass()" />
    </el-card>
  </div>

  <!-- 修改班级对话框 -->
  <el-dialog v-model="data.editClassDialogFormVisible" title="修改班级">
    <el-form :model="editClassForm" :rules="rules" ref="editClassFormRef">
      <el-form-item label="班级名称" :label-width="formLabelWidth" prop="name">
        <el-input v-model="editClassForm.name" autocomplete="off" placeholder="请选择班级名称" />
      </el-form-item>
      <el-form-item label="班级教室" :label-width="formLabelWidth" prop="classroom">
        <el-input v-model="editClassForm.classroom" autocomplete="off" placeholder="请选择班级教室" />
      </el-form-item>
      <el-form-item label="开课时间" :label-width="formLabelWidth" prop="from_time">
        <el-date-picker v-model="editClassForm.from_time" type="date" placeholder="请选择开课时间" />
      </el-form-item>
      <el-form-item label="结课时间" :label-width="formLabelWidth" prop="to_time">
        <el-date-picker v-model="editClassForm.to_time" type="date" placeholder="请选择结课时间" />
      </el-form-item>

      <el-form-item label="班主任" :label-width="formLabelWidth" prop="teacher">
        <el-select v-model="editClassForm.teacher" placeholder="请选择班主任">
          <el-option label="赵敏" value="赵敏" />
          <el-option label="徐坤" value="徐坤" />
        </el-select>
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.editClassDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="editClass()">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 新增班级对话框 -->
  <el-dialog v-model="data.newClassDialogFormVisible" title="新增班级">
    <el-form :model="newClassForm" :rules="rules" ref="newClassFormRef">
      <el-form-item label="班级名称" :label-width="formLabelWidth" prop="name">
        <el-input v-model="newClassForm.name" autocomplete="off" placeholder="请选择班级名称" />
      </el-form-item>
      <el-form-item label="班级教室" :label-width="formLabelWidth" prop="classroom">
        <el-input v-model="newClassForm.classroom" autocomplete="off" placeholder="请选择班级教室" />
      </el-form-item>
      <el-form-item label="开课时间" :label-width="formLabelWidth" prop="from_time">
        <el-date-picker v-model="newClassForm.from_time" type="date" placeholder="请选择开课时间" :size="size" />
      </el-form-item>
      <el-form-item label="结课时间" :label-width="formLabelWidth" prop="to_time">
        <el-date-picker v-model="newClassForm.to_time" type="date" placeholder="请选择开课时间" :size="size" />
      </el-form-item>

      <el-form-item label="班主任" :label-width="formLabelWidth" prop="teacher">
        <el-select v-model="newClassForm.teacher" placeholder="请选择班主任">
          <el-option label="赵敏" value="赵敏" />
          <el-option label="徐坤" value="徐坤" />
        </el-select>
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.newClassDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="addClass()">
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
  name: [
    { required: true, message: '请输入班级名称', trigger: 'change' },
    { min: 4, max: 30, message: '班级名称非法', trigger: 'change' },
  ],
  classroom: [

  ],
  from_time: [
    { required: true, message: '请输入班级名称', trigger: 'change' },
  ],
  to_time: [
    { required: true, message: '请输入班级名称', trigger: 'change' },
  ],
  teacher: [
    { required: true, message: '请输入班级名称', trigger: 'change' },
  ],
})


const editClassForm = reactive({
  id: '',
  name: '',
  classroom: '',
  from_time: '',
  to_time: '',
  teacher: '',
})

const newClassForm = reactive({
  id: '',
  name: '',
  classroom: '',
  from_time: '',
  to_time: '',
  teacher: '',
})

const data = reactive({
  hostname: '47.120.47.223',
  totalItem: 0,
  currentPage4: 1,
  pageSize4: 10,

  editClassDialogFormVisible: false,
  newClassDialogFormVisible: false,
  date1: '',//日期选择器日期
  tableData: [],
  classname: '',
})

const newClassFormRef = ref();
const editClassFormRef = ref();

function addClass() {

  newClassFormRef.value.validate((valid) => {
    if (valid) {
      axios({
        method: 'post',
        url: 'http://' + data.hostname + ':8081/addClass',
        data: {
          name: newClassForm.name,
          classroom: newClassForm.classroom,
          from_time: newClassForm.from_time,
          to_time: newClassForm.to_time,
          teacher: newClassForm.teacher,
        }
      }).then(function (response) {
        if (response.data.code == 1) {
          ElMessage({
            type: 'success',
            message: '添加成功',
          })
          getAllClass()
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
      data.newClassDialogFormVisible = false
    } else {
      ElMessage({
        type: 'error',
        message: '添加失败',
      })
    }
  })


}

function editClass() {

  editClassFormRef.value.validate((valid) => {
    if (valid) {
      axios({
        method: 'post',
        url: 'http://' + data.hostname + ':8081/editClass',
        data: {
          id: editClassForm.id,
          name: editClassForm.name,
          classroom: editClassForm.classroom,
          from_time: editClassForm.from_time,
          to_time: editClassForm.to_time,
          teacher: editClassForm.teacher,
        }
      }).then(function (response) {
        if (response.data.code == 1) {
          ElMessage({
            type: 'success',
            message: '修改成功',
          })
          getAllClass()
        } else {
          ElMessage({
            type: 'error',
            message: '修改失败',
          })
        }
        console.log(response);
      }).catch(function (error) {
        console.log(error);
      })
      data.editClassDialogFormVisible = false;
    } else {
      ElMessage({
        type: 'error',
        message: '修改失败',
      })
    }
  })


}

function editClassFormLoad(row) {
  data.editClassDialogFormVisible = true
  editClassForm.id = row.id;
  editClassForm.name = row.name;
  editClassForm.classroom = row.classroom;
  editClassForm.from_time = row.from_time;
  editClassForm.to_time = row.to_time;
  editClassForm.teacher = row.teacher;
}

function deleteClass(id) {
  //console.log('http://'+data.hostname+':8081/deleteClass/' + id)
  ElMessageBox.confirm(
    '您确定要删除该班级吗?',
    'Warning',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(() => {
    axios({
      method: 'delete',
      url: 'http://' + data.hostname + ':8081/deleteClass/' + id,
    }).then(function (response) {
      if (response.data.code == 1) {
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
        getAllClass()
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
  })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '删除取消',
      })
    })
}

function getAllClass() {
  axios({
    method: 'get',
    url: 'http://' + data.hostname + ':8081/getAllClass',
    params: {
      page: data.currentPage4,
      pageSize: data.pageSize4,
      name: data.classname,
      from_time: data.date1 == null ? null : data.date1[0],
      to_time: data.date1 == null ? null : data.date1[1],
    }
  }).then(function (response) {
    if (response.data.code == 1) {
      ElMessage({
        type: 'success',
        message: '数据加载成功',
      });
      data.totalItem = response.data.data.total
      data.tableData = response.data.data.rows;
    } else {
      ElMessage({
        type: 'error',
        message: '加载失败',
      })
    }
  }).catch(function (error) {
    console.log(error);
  });
}

getAllClass();

</script>

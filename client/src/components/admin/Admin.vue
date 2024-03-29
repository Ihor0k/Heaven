<template>
  <div class="admin">
    <draggable class="parameters"
               ghost-class="ghost"
               :list="parameters"
               handle=".header"
               @start="onDragStart"
               @end="onDragEnd"
    >
      <Parameter
          v-for="(parameter, index) in parameters"
          :key="parameter.key"
          ref="parameters"
          v-model="parameters[index]"
          @expand="expandParameter(index)"
          @remove="removeParameter(index)"
          @save="saveParameter(index)"
      />
    </draggable>
    <button class="button" @click="addParameter">Add parameter</button>
  </div>
</template>

<script>
import {KeyGenerator} from "../../utils"
import Vue from 'vue'
import Parameter from "./Parameter"
import axios from 'axios'
import draggable from 'vuedraggable'

require('@/assets/styles/common.css')

axios.defaults.baseURL = '/api'

export default {
  name: 'Admin',
  components: {
    Parameter,
    draggable
  },
  draggableParameter: null,
  keyGenerator: null,
  data() {
    return {
      parameters: []
    }
  },
  created() {
    this.$options.keyGenerator = new KeyGenerator()
    axios
        .get('/admin/parameters')
        .then(response => {
          response.data.forEach(parameter => Vue.set(parameter, 'key', this.$options.keyGenerator.next()))
          this.parameters = response.data
        })
  },
  methods: {
    addParameter: function () {
      const parameter = {
        key: this.$options.keyGenerator.next(),
        name: '',
        description: '',
        options: []
      }
      this.parameters.push(parameter)
      Vue.nextTick(() => {
        this.expandParameter(this.parameters.length - 1)
      })
    },
    expandParameter: function (index) {
      this.$refs.parameters.forEach((option, idx) => {
        if (idx === index) {
          option.expand()
        } else {
          option.collapse()
        }
      })
    },
    removeParameter: function (index) {
      const parameter = this.parameters[index]
      if (parameter.id) {
        axios
            .delete(`/admin/parameters/${parameter.id}`)
            .then(() => this.parameters.splice(index, 1))
      } else {
        this.parameters.splice(index, 1)
      }
    },
    saveParameter: function (index) {
      const parameter = this.parameters[index]
      const options = parameter.options.map(option => {
        const optionData = {
          name: option.name,
          description: option.description,
          price: option.price,
          image: option.image
        }
        if (option.id) {
          optionData.id = option.id
        }
        return (optionData)
      })
      const parameterData = {
        name: parameter.name,
        description: parameter.description,
        options: options
      }
      if (parameter.id) {
        axios.patch(`/admin/parameters/${parameter.id}`, parameterData)
      } else {
        axios
            .post('/admin/parameters', parameterData)
            .then(response => Vue.set(this.parameters, index, response.data))
      }
    },
    onDragStart: function (event) {
      this.$options.draggableParameter = this.parameters[event.oldIndex]
    },
    onDragEnd: function (event) {
      const parameter = this.$options.draggableParameter
      const position = event.newIndex
      this.$options.draggableParameter = null
      axios
          .post(`/admin/parameters/${parameter.id}/move`, null, {params: {position}})
    }
  }
}
</script>

<style scoped>
.admin {
  width: 100%;
}

.parameters {
  padding: 0;
  width: 50%;
  min-width: 50em;
  margin: auto;
}

.button {
  text-transform: uppercase;
  border-radius: 4px;
  border: 1px #2c3e50 solid;
  background-color: #42b983;
  color: #fdfbef;
  padding: 0.5em 1em;
}
</style>

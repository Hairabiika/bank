<template>
  <div>
    <h3>{{message}}</h3>
    <h3>{{clients}}</h3>
    <input type="text" v-model="clients.firstName" >
    <input type="text" v-model="clients.secondName" >
    <input type="text" v-model="clients.lastName" >
    <input type="text" v-model="clients.nickName" >
    <input type="number" v-model="clients.sex.id" >
    <br>
    <input type="number" v-model="clients.egn" >
    <input type="number" v-model="clients.city.id" >
    <input type="number" v-model="clients.current" >
    <input type="number" v-model="clients.saving" >
    <input type="number" v-model="clients.credit" >
    <br>
    <button v-on:click="saveClients">Редактирай</button>
    <router-link :to="{ name: 'Clients' }">Отвори</router-link>
  </div>
</template>

<script>
import ClientService from '../services/clients-service'

export default {
  name: 'ClientsTab',
  data () {
    return {
      message: '',
      clients: {
        id: '',
        firstName: '',
        secondName: '',
        lastName: '',
        nickName: '',
        sex:
          {
            id: '',
            type: ''
          },
        egn: '',
        city:
          {
            id: '',
            name: ''
          },
        current: '',
        saving: '',
        credit: ''
      }
    }
  },
  beforeRouteEnter (to, from, next) {
    console.log(to)
    if (to.params.id) {
      ClientService.getClientsId(to.params.id).then(
        response =>
          next(vm =>
            vm.setData(response)
          )
      )
    } else {
      next()
    }
  },
  methods: {
    setData (response) {
      this.clients = response.data
    },
    saveClients () {
      ClientService.saveClients(this.clients).then(
        response => {
          console.log(response)
          this.clients = response.data.clients
          this.message = response.data.message
        },
        error => {
          this.message =
            (error.data.message.toString()) ||
            error.message ||
            (error.response && error.response.data)
        }
      )
    }
  }
}
</script>

<style scoped>

</style>

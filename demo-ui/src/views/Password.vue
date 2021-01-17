<template>
  <div>
    <h3>{{message}}</h3>
    <input v-model="pass" id="pass" type='password'>
    <router-link :to="{ name: 'ClientsInfo', params: { id:accessId, pass:pass }}">Отвори</router-link>
    <br>
    <router-link :to="{ name: 'Clients' }">Върни се в главното меню</router-link>
  </div>
</template>
<script>
import ClientService from '../services/clients-service'

export default {
  name: 'Password',
  data () {
    return {
      message: '',
      accessId: '',
      pass: '',
      clients: [{
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
      }],
      fields: [
        { key: 'actions' }
      ],
      filters: {
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
    ClientService.getClientsId(to.params.id).then(
      response =>
        next(vm =>
          vm.setData(response, to.params.id)
        )
    )
  },
  methods: {
    setData (response, id) {
      this.clients = response.data
      this.accessId = id
    },
    checkPass () {
      if (this.pass === 0) {
        this.$router.push('/clientsInfo')
      }
    },
    accessClients (id) {
      ClientService.accessClients(id).then(
        response => {
          console.log(response)
          this.message = response.data
          this.searchClients()
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        }
      )
    },
    searchClients () {
      ClientService.getClientsPage(this.filters, this.currentPage, this.perPage).then(
        response => {
          console.log(response)
          this.clients = response.data.clients
          this.rows = response.data.totalItems
        },
        error => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        }
      )
    }
  }
}
</script>

<style scoped>

</style>

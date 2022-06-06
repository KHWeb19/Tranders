import {
    FETCH_MESSAGE_LIST,
    FETCH_MESSAGE,
} from './mutation-types'

import axios from 'axios'

export default {
    fetchMessageList ({ commit }) {
        return axios.get('http://localhost:7777/kafka/list')
            .then((res) => {
                commit(FETCH_MESSAGE_LIST, res.data)
            })
    },
    fetchMessage ({ commit }) {
        return axios.get('http://localhost:7777/kafka')
            .then((res) => {
                commit(FETCH_MESSAGE, res.data)
            })
    },
}
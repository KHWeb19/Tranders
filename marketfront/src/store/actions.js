import {
    FETCH_MESSAGE_LIST,
} from './mutation-types'

import axios from 'axios'

export default {
    fetchMessageList ({ commit }) {
        return axios.get('http://localhost:7777/kafka/list')
            .then((res) => {
                commit(FETCH_MESSAGE_LIST, res.data)
            })
    },
}
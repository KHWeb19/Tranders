import {
    FETCH_MESSAGE,
} from './mutation-types'

import axios from 'axios'

export default {
    fetchMessage ({ commit }) {
        return axios.get(`http://localhost:7777/kafka/sendMessage`)
                .then((res) => {
                    commit(FETCH_MESSAGE, res.data)
                })
    }
}
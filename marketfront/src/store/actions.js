import {
    FETCH_CHATROOM_LIST,
} from './mutation-types'

import axios from 'axios'

export default {
    fetchChatroomList ({ commit }) {
        return axios.get('http://localhost:7777/chatting/list')
            .then((res) => {
                commit(FETCH_CHATROOM_LIST, res.data)
            })
    },
}
import {
    FETCH_CHATROOM_LIST,
} from './mutation-types'

export default {
    [FETCH_CHATROOM_LIST] (state, chatrooms) {
        state.chatrooms = chatrooms
    },
}
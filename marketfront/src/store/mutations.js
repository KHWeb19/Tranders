import {
    FETCH_MESSAGE_LIST,
    FETCH_MESSAGE
} from './mutation-types'

export default {
    [FETCH_MESSAGE_LIST] (state, messages) {
        state.messages = messages
    },
    [FETCH_MESSAGE] (state, message) {
        state.message = message
    }
}
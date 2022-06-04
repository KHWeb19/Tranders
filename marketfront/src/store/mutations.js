import {
    FETCH_MESSAGE_LIST
} from './mutation-types'

export default {
    [FETCH_MESSAGE_LIST] (state, messages) {
        state.messages = messages
    }
}
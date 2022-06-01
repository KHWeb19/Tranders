import {
    FETCH_MESSAGE
} from './mutation-types'

export default {
    [FETCH_MESSAGE] (state, message) {
        state.message = message
    }
}
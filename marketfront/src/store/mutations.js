import {
    FETCH_MESSAGE_LIST
} from './mutation-types'

export default {
    [FETCH_MESSAGE_LIST] (state, massages) {
        state.massages = massages
    }
}
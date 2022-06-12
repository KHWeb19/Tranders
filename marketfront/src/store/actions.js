import {
    FETCH_REFRESH_TOKEN

} from './mutation-types'
import axios from "axios";

export default {
    refreshToken ({commit}) {
        return axios.get('http://localhost:7777/member/refreshToken',{
            headers: {
                'Authorization': 'Bearer '+localStorage.getItem('refresh_token'),
                'Accept' : 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then((res) => {
                commit(FETCH_REFRESH_TOKEN, res.data)
                console.log('refresh Token')
            })
    }
}
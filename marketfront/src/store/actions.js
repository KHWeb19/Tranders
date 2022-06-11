import {
    FETCH_PRODUCT,
    FETCH_PRODUCT_LIST,
} from './mutation-types'

import axios from 'axios'

export default {
    fetchProduct({ commit }) {
        return axios.get('http://localhost:7777/product/${productNo}')
            .then((res) => {
                commit(FETCH_PRODUCT, res.data)
            })
    },
    fetchProductList({ commit }) {
        return axios.get('http://localhost:7777/product/list')
            .then((res) => {
                commit(FETCH_PRODUCT_LIST, res.data)
            })
    }
}
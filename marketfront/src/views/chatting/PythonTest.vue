<template>
  <div>
    {{new_data}}
    <table :data="new_data">
      <template slot-scope="{row}">
                        <th >
                            {{row.content}} 위
                        </th>

                    </template>
    </table>
  </div>
</template>

<script>
import axios from "axios";

    export default {
        name: 'my-little-div',
        data() {
            return {
                new_data: [],
            }
        },
        methods: {
            getNewData() {
                let path = 'http://127.0.0.1:5000/kafka-data';
                axios.get(path).then((res) => {
                  console.log(res.data)
                    this.new_data = res.data;
                }).catch((error) => {
                  
                    console.error(error);
                });
            },
        },
        created() {
            this.getNewData();
        }
    };
</script>
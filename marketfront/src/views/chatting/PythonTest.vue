<template>
<div>
  <div v-for="msg in new_data" :key="msg.messageNo">
    {{msg}}
    <table :data="new_data">
      <template slot-scope="{row}">
                        <th >
                            {{row.content}} 위
                        </th>

                    </template>
    </table>
  </div>
</div>
</template>

<script>
import axios from "axios";

    export default {
        name: 'PythonTest',
        data() {
            return {
                new_data: [],
            }
        },
        methods: {
            getNewData() {
                axios.get('http://127.0.0.1:5000/kafka-data')
                  .then((res) => {
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
<template>
    <div class="tango-widget">
        <v-card elevation="1">
            <v-card-title> TANGO Device Info </v-card-title>
            <v-card-text>
                <v-row>
                    <v-col class="col1">
                        <div class="message-box-container">
                            <div v-if="!!status" class="message-box">
                                <span v-if="status.type === 'error'">
                                    <v-icon> mdi-alert-circle </v-icon>
                                    {{ status.message }}
                                </span>
                                <span v-if="status.type === 'success'">
                                    <pre>{{status.message}}</pre>
                                </span>
                            </div>
                        </div>
                        <v-btn elevation="1" @click="tangoTest" class="full-width" v-if="update"> Update </v-btn>
                        <v-btn elevation="1" @click="tangoTest" class="full-width" v-else> Connect </v-btn>
                    </v-col>
                    <v-col class="col2" v-if="update">
                        <v-data-table
                           dense
                           :headers="headers"
                           :items="attributes"
                           :items-per-page="4"
                           item-key="name"
                           class="elevation-1">
                        </v-data-table>
                    </v-col>
                </v-row>
            </v-card-text>
        </v-card>
    </div>
</template>
<script>
export default {
    data() {
        return {
            loading: false,
            update: false,
            status: {
                type: '',
                message: '',
            },
            attributes: [],
              headers: [
                { text: 'Attribute Name', align: 'start', value: 'name' },
                { text: 'Type', value: 'type' },
              ],
        };
    },

    props: {
        datasource: {
            required: true,
            type: Object,
        },
    },

    methods: {
        tangoTest() {
            this.loading = true;
            this.status = null;
            this.$store
                .dispatch('tangoDeviceInfo', { datasource: this.datasource })
                .then((r) => {
                    if(r.response.toLowerCase().includes('fail')) {
                        this.update = false;
                        this.status = {
                            type: 'error',
                            message: this.$t(r.response),
                        };
                    } else {
                        this.attributes = JSON.parse(r.attributes);
                        this.update = true;
                            this.status = {
                            type: 'success',
                        message: r.response,
                        }
                    };
                })
                .catch((e) => {
                    this.update = false;
                    this.status = {
                        type: 'error',
                        message: this.$t(e.data.response),
                    };
                })
                .finally(() => {
                    this.loading = false;
                });
        },
    },
};
</script>
<style scoped>
.message-box-container {
    min-height: 40px;
    box-shadow: inset 1px 2px 4px 0px #aaa;
    border-radius: 5px;
    background-color: #0000000d;
    margin: 5px;
    display: flex;
    justify-content: center;
}
.message-box {
    margin: 10px;
    font-style: italic;
}
.tango-widget {
    max-width: 1000px;
}
.col1 {
    min-width: 400px;
}
.col2 {
    max-width: 400px;
}
.full-width {
    width: 100%;
}
/style>

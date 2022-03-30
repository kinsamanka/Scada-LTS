<template>
	<DataSourceConfig
		title="TANGO Data Source"
		:datasource="datasource"
		:creator="createMode"
		:polling="false"
		@cancel="cancel()"
		@accept="save()"
	>
		<v-row>
			<v-col cols="3">
				<v-text-field
					v-model="datasource.hostName"
					placeholder="localhost"
					label="TANGO Host"
				></v-text-field>
			</v-col>
			<v-col cols="3">
				<v-text-field
					v-model="datasource.port"
					placeholder="10000"
					label="Port Number"
				></v-text-field>
			</v-col>
			<v-col cols="3">
				<v-text-field
					v-model="datasource.deviceID"
					placeholder="sys/tg_test/1"
					label="Device Address"
				></v-text-field>
			</v-col>
		</v-row>
	</DataSourceConfig>
</template>
<script>
import DataSourceConfigMixin from '../DataSourceConfigMixin';

export default {
	mixins: [DataSourceConfigMixin],

};
</script>
<style></style>

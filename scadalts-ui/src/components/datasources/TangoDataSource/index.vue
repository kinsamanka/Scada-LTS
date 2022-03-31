<template>
	<DataSourceLoader :loaded="detailsLoaded" ref="dialog">
		<template v-slot:config>
			<config
				v-if="detailsLoaded"
				@canceled="closeEditor()"
				@saved="onSaved($event)"
				:datasource="ds"
				:createMode="false"
				:polling="false"
			/>
		</template>
		<v-row>
			<v-col>
				<tangoTest :datasource="ds"> </tangoTest>
			</v-col>

			<!-- TODO: Implement ReadData Component and NodeScan -->
		</v-row>
	</DataSourceLoader>
</template>
<script>
import DataSourceBase from '../DataSourceBase.vue';
import config from './config.vue';
import tangoTest from './components/tangoTest.vue';

export default {
	extends: DataSourceBase,

	components: {
	    config,
	    tangoTest,
	},

	data() {
		return {
			ds: undefined,
		};
	},

	mounted() {
		this.fetchData();
	},

	methods: {
		async fetchData() {
			let res = await this.fetchDataSourceDetails();
			this.ds = res;
		},
	},
};
</script>
<style scoped></style>

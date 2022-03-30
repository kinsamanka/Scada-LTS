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
	</DataSourceLoader>
</template>
<script>
import DataSourceBase from '../DataSourceBase.vue';
import config from './config.vue';

export default {
	extends: DataSourceBase,

	components: { config },

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

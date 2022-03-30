<template>
	<div>
		<DataPointCreation
			title="TANGO Data Point"
			:creator="createMode"
			:datapoint="datapoint"
			@cancel="cancel()"
			@accept="save()"
		>
			<template v-slot:selector>
				<v-select
					v-model="datapoint.pointLocator.dataTypeId"
					:items="datapointTypes"
				></v-select>
			</template>

			<v-row>
				<v-col>
					<v-text-field
						label="Attribute"
						v-model="datapoint.pointLocator.attribute"
						:rules="[ruleNotNull]"
						required
					></v-text-field>
				</v-col>
			</v-row>
		</DataPointCreation>
	</div>
</template>
<script>
import DataPointCreation from '../DataPointCreation';
import { DataTypes, DataChangeTypes } from '@/store/dataSource/constants';

export default {
	components: {
		DataPointCreation,
	},

	props: {
		createMode: {
			type: Boolean,
			default: true,
		},
		datapoint: {
			required: true,
			type: Object,
		},
	},

	computed: {
		datapointTypes() {
			return this.$store.state.dataSourceState.datapointTypes;
		},
	},

	data() {
		return {
			DataTypes: DataTypes,
			DataChangeTypes: DataChangeTypes,
			ruleNotNull: (v) => !!v || this.$t('validation.rule.notNull'),
		};
	},

	methods: {
		cancel() {
			this.$emit('canceled');
		},

		save() {
			this.$emit('saved', this.datapoint);
		},
	},
};
</script>
<style></style>

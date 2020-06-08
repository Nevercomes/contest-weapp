<template>
	<div class="app-container">
		<!-- 搜索部分 -->
		<van-search :value="keyword" use-action-slot placeholder="请输入搜索关键词" @focus="goToSearchPage">
			<view style="display: flex; align-items: center;" slot="action" @click="goToPublicPost">
				<van-icon size="24" name="add"></van-icon>
			</view>
		</van-search>
		
		<!-- 这里是tab -->
		
		<!-- 这里是主要内容 -->
		
	</div>
</template>

<script>
	export default {
		name: 'NewsIndex',
		data() {
			return {
				
			}
		},
		methods: {
			goToSearchPage() {
				uni.navigateTo({
					url: 'search-index?type=all'
				})
			},
			// 取代搜索事件改为setting设置
			goToPublicPost() {
				uni.navigateTo({
					url: 'news-public-form'
				})
			},
		}
	}
</script>

<style>
</style>

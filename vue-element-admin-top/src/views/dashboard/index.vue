<template>
    <div id="map-container" class="map-container"></div>
</template>

<script>
    import dynamicLoadScript from "@/utils/dynamicLoadScript";

    export default {
        name: 'Dashboard',
        data() {
            return {
                currentRole: 'adminDashboard'
            }
        },
        mounted() {
            this.initMap();
        },
        methods: {
            initMap() {
                let mapUrl = 'https://webapi.amap.com/maps?v=2.0&key=741f14a10530a9945dbc306f27c377a6'
                dynamicLoadScript(mapUrl, (err) => {
                    if (err) {
                        return
                    }
                    console.log(AMap)
                    const option = {
                        zoom: 11,
                        center: [116.397428, 39.90923],
                        viewMode: '3D',
                        mapStyle: 'amap://styles/b3aafb545e7d7813b3d7afaf0cde299f'
                    };
                    const map = new AMap.Map('map-container', option);
                })
            }
        }
    }
</script>
<style lang="scss" scoped>
    .map-container{
        overflow: hidden;
        margin: -10px;
        height: calc(100vh - 60px);
    }
</style>

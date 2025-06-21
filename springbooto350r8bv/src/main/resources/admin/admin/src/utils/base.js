const base = {
    get() {
        return {
            url : "http://localhost:8080/springbooto350r8bv/",
            name: "springbooto350r8bv",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbooto350r8bv/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "智慧农业专家远程指导系统"
        } 
    }
}
export default base

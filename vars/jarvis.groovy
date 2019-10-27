def call(Closure body) {
    def jarvis = new Object() {
        def version
        def version(String version) {
            this.version = version
        }
        def context(context) {
            context.metaClass.methodMissing {
                println "name = ${name}"
                println "args[0] = ${args[0]}"
                return context
            }
        }
    }
    body.setDelegate(jarvis)
    body.setResolveStrategy(Closure.DELEGATE_FIRST)
    body.call()
    library "jarvis@${jarvis.version}"
}

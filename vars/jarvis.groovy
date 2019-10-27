def context = this

def call(Closure body) {
    def jarvis = new Object() {
        def version
        def version(String version) {
            this.version = version
        }
        def context(context) {
            delegate.metaClass.methodMissing {
                println "name = ${name}"
                println "args[0] = ${args[0]}"
                return delegate
            }
        }
    }
    body.setDelegate(jarvis)
    body.setResolveStrategy(Closure.DELEGATE_FIRST)
    body.call()
    library "jarvis@${jarvis.version}"
}

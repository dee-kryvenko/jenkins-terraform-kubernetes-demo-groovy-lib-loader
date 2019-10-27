def context = this

def call(Closure body) {
    def jarvis = new Object() {
        def version
        def version(String version) {
            this.version = version
        }
    }
    body.setDelegate(jarvis)
    body.setResolveStrategy(Closure.DELEGATE_FIRST)
    body.call()
    library "jarvis@${jarvis.version}"
}

def methodMissing(String name, args) {
    println "name = ${name}"
    println "args[0] = ${args[0]}"
    return context
}

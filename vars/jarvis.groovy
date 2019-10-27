def call(Closure body) {
    def jarvis = new Object() {
        def version
        void setJarvis(context) {
            context.metaClass.methodMissing { String name, args ->
                println "Patching for HCL, part 1"
                return context.jarvisHcl.get(name, args)
            }
        }
    }
    body.setDelegate(jarvis)
    body.setResolveStrategy(Closure.DELEGATE_FIRST)
    body.call()
    library "jarvis@${jarvis.version}"
}

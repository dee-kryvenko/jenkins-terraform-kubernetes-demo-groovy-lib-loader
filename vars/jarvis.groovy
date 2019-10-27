def call(Closure body) {
    def jarvis = new Object() {
        def version
        def use(context) {
            context.metaClass.methodMissing { String name, args ->
                return context.jarvisHcl.get(name, args)
            }
        }
    }
    body.setDelegate(jarvis)
    body.setResolveStrategy(Closure.DELEGATE_FIRST)
    body.call()
    library "jarvis@${jarvis.version}"
}

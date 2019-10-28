def call(Closure body) {
    def jarvis = new Object() {
        def version
        void jarvis(context) {
            context.metaClass.invokeMethod { String name, args ->
                return context.jarvisHcl.get(context, name, args)
            }
        }
    }
    body.setDelegate(jarvis)
    body.setResolveStrategy(Closure.DELEGATE_FIRST)
    body.call()
    library "jarvis@${jarvis.version}"
}

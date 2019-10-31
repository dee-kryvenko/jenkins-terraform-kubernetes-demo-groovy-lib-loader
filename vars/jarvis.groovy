def call(Closure body) {
    def jarvis = new Object() {
        def version
        void jarvis(context) {
            context.metaClass.methodMissing { String name, args ->
                return context.jarvisHcl.get(context, name, args)
            }
        }
    }
    body.setDelegate(jarvis)
    body.setResolveStrategy(Closure.DELEGATE_ONLY)
    body.call()
    library "jarvis@${jarvis.version}"
}

def call(context) {
    context.jarvisHcl.done(context.steps)
}

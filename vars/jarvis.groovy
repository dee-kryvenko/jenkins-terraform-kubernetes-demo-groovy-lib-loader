def call(Closure body) {
    def context = body.thisObject
    context.metaClass.methodMissing { String name, args ->
        return context.jarvisHcl.get(context, name, args)
    }
    def jarvis = new Object() {
        def version
    }
    body.setDelegate(jarvis)
    body.setResolveStrategy(Closure.DELEGATE_ONLY)
    body.call()
    library "jarvis@${jarvis.version}"
}

def call(context) {
    context.jarvisHcl.done(context.steps)
}

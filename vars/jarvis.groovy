def call(Closure body) {
    def jarvis = new Object() {
        def version
        void jarvis(context) {
            // context.metaClass.originalMethodMissing = context.metaClass.methodMissing
            context.metaClass.originalMethodMissing = context.metaClass.getMetaMethod("methodMissing", [String, List] as Class[])
            context.metaClass.methodMissing { String name, args ->
                return context.jarvisHcl.get(context, name, args)
            }
        }
    }
    body.setDelegate(jarvis)
    body.setResolveStrategy(Closure.DELEGATE_FIRST)
    body.call()
    library "jarvis@${jarvis.version}"
}

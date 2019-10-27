def call(Closure body) {
    def jarvis = new Object() {
        def version
        void jarvis(context) {
            def originalMethodMissing = context.metaClass.&methodMissing
            context.metaClass.methodMissing { String name, args ->
                return context.jarvisHcl.get(context, originalMethodMissing, name, args)
            }
        }
    }
    body.setDelegate(jarvis)
    body.setResolveStrategy(Closure.DELEGATE_FIRST)
    body.call()
    library "jarvis@${jarvis.version}"
}

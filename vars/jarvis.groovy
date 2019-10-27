def call(Closure body) {
    def jarvis = new Object() {
        def version
        void jarvis(context) {
            context.metaClass.methodMissing { String name, args ->
                if (args.size() != 1 || args[0] instanceof String) {
                    throw new MissingMethodException(name, context.class, args)
                }
                return context.jarvisHcl.get(context, name, args)
            }
        }
    }
    body.setDelegate(jarvis)
    body.setResolveStrategy(Closure.DELEGATE_FIRST)
    body.call()
    library "jarvis@${jarvis.version}"
}

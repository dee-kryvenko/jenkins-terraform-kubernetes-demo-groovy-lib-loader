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

  if (args.length == 3 && args[0] instanceof String && args[1] instanceof String && args[2] instanceof Closure) {
    println "${name}.${args[0]}.${args[1]}"
  }

  throw new MissingMethodException(name, this.class, args)
}

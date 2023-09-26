def call(String appName, String environment) {
    script {
        echo "Deploying ${appName} to ${environment environment"
        org.mycompany.utils.Myutilities.printHello()
    }
}
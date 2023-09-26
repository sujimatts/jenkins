// shared_library/vars/createDirectory.groovy

def call() {
    script {
        // Define the directory path
        def directoryPath = 'abcd'

        // Create the directory
        def directory = new File(directoryPath)
        if (!directory.exists()) {
            directory.mkdirs()
            echo "Directory '${directoryPath}' created."
        } else {
            echo "Directory '${directoryPath}' already exists."
        }

        // Get the current timestamp
        def timestamp = new Date().format("yyyy-MM-dd HH:mm:ss")

        // Store the timestamp in a file
        def file = new File("${directoryPath}/file.txt")
        file.text = "Timestamp: ${timestamp}"
        echo "Timestamp '${timestamp}' stored in 'file.txt'."
    }
}

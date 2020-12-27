pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }
    triggers {
            cron('0 8 * * *')
            }
            parameters {
                gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'BRANCH', type: 'PT_BRANCH'
                string(defaultValue: '', description: '', name: 'email', trim: false)
                string(defaultValue: '', description: '', name: 'password', trim: false)
                string(defaultValue: '', description: '', name: 'profile', trim: false)
                string(defaultValue: '', description: '', name: 'numberofprofilestofollow', trim: false)
                string(defaultValue: '', description: '', name: 'user', trim: false)
                string(defaultValue: '', description: '', name: 'dbPassword', trim: false)
            }

    stages {
        stage('Running tests') {
            steps {
                // Get some code from a GitHub repository
                git branch: "${params.BRANCH}", url: 'https://github.com/KazadaevIvan/InstagramBot.git'

                // Run Maven on a Unix agent.
                sh "mvn clean test -Demail=${email.password} -Dpassword=${params.password} -Dprofile=${params.profile} -Dnumberofprofilestofollow=${params.numberofprofilestofollow} -Duser=${params.user} -DdbPassword=${params.dbPassword} -Dmaven.test.failure.ignore=true"

                // To run Maven on a Windows agent, use
                //bat "mvn clean test -Dmaven.test.failure.ignore=true"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('Generating Allure Report') {
            steps {
                script {
                     allure([
                             includeProperties: false,
                             jdk: '',
                             properties: [],
                             reportBuildPolicy: 'ALWAYS',
                             results: [[path: 'target/allure-results']]
                     ])
                 }
            }
        }
    }
}
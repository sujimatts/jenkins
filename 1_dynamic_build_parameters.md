While working with the Jenkins jobs (whether it’s declarative or freestyle), we often need to pass some parameters to the code being executed when you trigger the job. Jenkins supports this use-case by means of parameters that you can declare and use as Groovy variables in your Jenkins job. However, often you are not aware of all the parameters in the beginning or sometimes you want to render the parameters dynamically based on the value selected in any other parameter. Given the declarative nature of Jenkins jobs, we cannot achieve the use-case with the native Jenkins parameters available. Here comes the Active Choices parameter plugin to the rescue, which can help us render parameters/parameter’s value dynamically.

Active Choices parameter plugin provides 3 types of parameters.

  - Active Choices Parameter
  - Active Choices Reactive Parameter
  - Active Choices Reactive Reference Parameter
Let’s discuss each of these parameters and what they do.

## Active Choices Parameter
An Active Choices parameter dynamically generates a list of value options for a build parameter using a Groovy script or a script from the Scriptler catalog.
Active Choices parameters can be rendered as standard selection lists, checkboxes, and radio buttons.
A text box filter can be optionally shown to aid in filtering the value options.

## Active Choices Reactive Parameter
An Active Choices Reactive Parameter can generate the same set of value options as of Active choice parameter; in addition to that, it can be dynamically updated with the values based on the selection in some other build parameter that was referred to in the Active choices reactive parameter.
Active Choices Reactive parameters can be rendered as standard selection lists, checkboxes, and radio buttons.
A text box filter can be optionally shown to aid in filtering the value options.

## Active Choices Reactive Reference Parameter
This parameter provides the same features as the Active Choices Reactive Reference parameters.
In addition to that, it can enhance the Job UI form with custom HTML controls like list, images, text boxes, etc. We can render the dynamically generated HTML from Groovy script, and it can render it well on the UI page. We can add JavaScript and CSS styling as well to the HTML controls we are rendering using this parameter.

##Active Choices use cases

## Active Choices Parameter
When we want to generate the dropdown of checkboxes dynamically via some value returned from the API. For example, we can make an API call to fetch all the country’s states and return it as a Groovy list so they will be rendered as a dropdown list.

## Active Choices Reactive Parameter
When we want to generate the dropdown of checkboxes based on the value returned from an API call plus selection in other dependent build parameters. Consider the above example of state, if we want to render all the cities for the state selected then we can use the Active Choices Reactive parameter and refer to the state parameter so that we can use the value of state parameter in the Groovy script for cities parameter.

## Active Choices Reactive Reference Parameter
Think of a deployment pipeline for a 3 tier application where we have to deploy multiple services for each tier. If we want the user to select which services he/she wants to deploy along with the release tag for that service, we need one extra control to ask the user for the release tag for the service he/she has selected. We can achieve this using the Active Choices Reactive Reference Parameter as it lets us render HTML components dynamically using some Groovy script. So, we can have a checkbox and textbox side-by-side which was not possible in earlier cases. Detailed explanation on the use case in this section

## Prerequisites
This plugin requires Jenkins version >= 2.204.1.
The Active Choices Parameter plugin is installed on Jenkins.

## Render values dynamically based on the selection in other build parameter
In this use case, we will see how to render a dropdown with dynamic values using Active Choices Reactive parameters. We will see how we can configure the Active Choices parameter and Active Choices Reactive parameter.

  -  We will first create a sample pipeline project for this. Login to Jenkins, click on New Item, in the next page provide the name of your choice for your pipeline and select the Pipeline and click on Ok.
  - On the configure job page select the This project is parameterized checkbox in the general tab.
  
  ![Alt Text](https://d33wubrfki0l68.cloudfront.net/b3dfb8e5ead4ab9d6f8963ad3e9d6ae8b016003d/d5f79/assets/img/blog/render-jenkins-build-parameters-dynamically/create-pipeline-active-choice.gif)

  - Now click on the Add Parameters dropdown and select the Active Choices Parameter from the list.
  - The parameter configuration panel will look as shown in the image above.
  - In the Name field add any name for your parameters and select the Groovy script in the Script radio buttons. Add a suitable description for your parameter.
  - Next is Choice Type dropdown which has 4 choices available.
      - Single Select : It will render a dropdown list from which a user can select only one value. Value will be accessible in the pipeline code via a variable name.
      - Multi Select : It will render a dropdown list from which a user can select multiple values. If the user has selected multiple values then in the pipeline code value will be passed as comma separated string, we can get the value via parameter name as we would have got it for any other parameter.
      - Radio Buttons : It will render a radio button group for the list we have returned from the Groovy script. This will be a single selection parameter only.
      - Checkboxes : It will render a checkbox group for the list we have returned from the Groovy script. If we select the multiple checkboxes then the values will be comma separated.
  - Now we will add the values to this parameter and will see it in action. (Refer the GIF above for how we can add the values). Add the following code in the Groovy script box.
  
  
  ```return ["Gujarat", "Maharashtra", "Punjab"]```
  
  - This will render a dropdown list with these values. Fallback script is used when there is some exception in the script execution. Save the job and open the Build with parameters page.
  - This is how it will be visible on the Build With Parameters page.
  
  ![Alt Text](https://d33wubrfki0l68.cloudfront.net/1fe1e6d41e19f50dca701d510332f8c5d484bb4b/e31b2/assets/img/blog/render-jenkins-build-parameters-dynamically/job-build-with-parameters.png)
  
  Now we will add an Active Choices Reactive Parameter which will update the values based on the selection in the states parameter. We will render the cities for the states we rendered.

    - Click on the configure and scroll down to the states parameter we have added.
    - Click again on the add parameter dropdown and select the Active Choices Reactive Parameter.
  
  ![Alt Text](https://d33wubrfki0l68.cloudfront.net/a8f119589746b624772fa597aaea76e92f7efe1f/0ff3f/assets/img/blog/render-jenkins-build-parameters-dynamically/pipeline-active-choices-reactive-parameter.gif)
  
    - Fill in the following code in the Groovy script box.
    
    ```
    if ( states == "Gujarat") {
  return ["Rajkot", "Ahmedabad", "Jamnagar"]
} else if ( states == "Maharashtra") {
  return ["Mumbai", "Pune", "Nagpur"]
} else if (states == "Punjab") {
  return ["Ludhiana", "Amritsar", "Jalandhar"]
}
 
```
    - Here, we have referenced the states parameter and used it’s value in the Groovy script to render the values for the cities for that state. Now, hit the save button and click on the Build with parameters.
It will look something like this on the Build with parameters page. Based on the selection in the states dropdown list the radio buttons will be updated.  


  
  
  
  
  
  
  
  
  

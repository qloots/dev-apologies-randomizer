<h3 align="center">Dev Apologies Generate</h3>

<p align="center">
An application which will generate random apologies for a developer
</p>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/qloots/dev-apologies-randomizer">
    <img src="src/main/resources/img/sorry.jpg" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Dev Apologies Randomizer</h3>

  <p align="center">
    <br />
    <a href="https://github.com/qloots/dev-apologies-randomizer"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/qloots/dev-apologies-randomizer">View Demo</a>
    ·
    <a href="https://github.com/qloots/dev-apologies-randomizer/issues">Report Bug</a>
    ·
    <a href="https://github.com/qloots/dev-apologies-randomizer/issues">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

You are a developer and a customer is asking you something?  
Here is the random apologies generator you need to make him waiting !

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* Java 17
* Maven
* Docker
* Postgres

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

To run the project, you will need : 
* Java 17
* Maven
* Docker

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/qloots/dev-apologies-randomizer.git
   ```
2. Open it in your favorite IDE
3. Create the jar file using your IDE or from your terminal:
   ```sh
   mvn clean package -DspkipTest
   ```
4. Build the Docker image
   ```sh
   docker compose build
   ```
5. Run the Java Application :
   ```sh
   docker compose up dev_app
   ``` 
6. Enjoy it with Postman in a fist time

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

In a first time, you can use Postman to use the project.  
``GET http://localhost:8080/api/apologies`` will return you all the available apologies.

``GET http://localhost:8080/api/ping`` will return you `Pong` as body response.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feat/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some Amazing Feature'`)
4. Push to the Branch (`git push origin feat/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>
# Docker - App

Docker is a platform for developers and sysadmins to build, run, and share applications with containers. The use of containers to deploy applications is called containerization.

<h3>Images and containers</h3>


Fundamentally, a container is nothing but a running process, with some added encapsulation features applied to it in order to keep it isolated from the host and from other containers. One of the most important aspects of container isolation is that each container interacts with its own private filesystem; this filesystem is provided by a Docker image. 

An image includes everything needed to run an application - the code or binary, runtimes, dependencies, and any other filesystem objects required.



<h3>Containers and virtual machines</h3>


A container runs natively on Linux and shares the kernel of the host machine with other containers. It runs a discrete process, taking no more memory than any other executable, making it lightweight.

By contrast, a virtual machine (VM) runs a full-blown “guest” operating system with virtual access to host resources through a hypervisor. In general, VMs incur a lot of overhead beyond what is being consumed by your application logic.


Below are command which helps in building dockerized application

```
docker --version                      => check docker version
docker image ls                       => list images that you downloaded to your machine.
docker image rm --force <image-id>    => remove image
docker ps --all                       => List all containers
```

<h3>Define a container with Dockerfile</h3>


Take a look at the file called Dockerfile in the bulletin board application. Dockerfiles describe how to assemble a private filesystem for a container, and can also contain some metadata describing how to run a container based on this image.



```
# Use the official image as a parent image.
FROM node:current-slim

# Set the working directory.
WORKDIR /usr/src/app

# Copy the file from your host to your current location.
COPY package.json .

# Run the command inside your image filesystem.
RUN npm install

# Inform Docker that the container is listening on the specified port at runtime.
EXPOSE 8080

# Run the specified command within the container.
CMD [ "npm", "start" ]

# Copy the rest of your app's source code from your host to your image filesystem.
COPY . .
```

<h3>Build your image</h3>


```
docker build --tag bulletinboard:1.0 .
```

<h3>Run your image as a container</h3>

1. Start a container based on your new image:

```
docker run --publish 8000:8080 --detach --name bb bulletinboard:1.0
```

There are a couple of common flags here:

	- --publish asks Docker to forward traffic incoming on the host’s port 8000, to the container’s port 8080. Containers have their own private set of ports, so if you want to reach one from the network, you have to forward traffic to it in this way. Otherwise, firewall rules will prevent all network traffic from reaching your container, as a default security posture.
	- --detach asks Docker to run this container in the background.
	- --name specifies a name with which you can refer to your container in subsequent commands, in this case bb.

2. Visit your application in a browser at localhost:8000.
3. Once you’re satisfied that your bulletin board container works correctly, you can delete it:
   
```
docker rm --force bb
```

The --force option removes the running container. If you stop the container running with docker stop bb you do not need to use --force.


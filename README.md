

# Description

## Introduction

Cooperation

* The action or process of working together to the same end.
* Assistance, especially by complying readily with requests.
* The formation and operation of cooperatives.

The essential core of this project is the idea that all cooperative effort must have well defined aims and that translate into goals and thence to tasks, which the persons cooperating perform to achieve those goals and aims. 

The aim of this project is to provided a platform for recording these aims, goals and tasks, and then to allow a record to be kept of other related events which determine, ultimately, if the aims are being (or have been) achieved.

All for-profit organisations have at least one aim, to be profitable. How a for-profit business achieves profitability is usually the subject of a business plan. So this project could be used to create a business plan and monitor its execution (probably through integration with an accounting package). [Large organisations usually employ complex software to assist in this called 'Enterprise Resource Planning (ERP)' systems.]

A not-for-profit organisation with an on-going source of funding won't have profitability as an aim, but will have other aims. So its goals should relate to maximising the achievement of its aims within its fixed income.

A formal cooperative organisation, one owned by its members, will also have defined aims and these must be stated explicitly to allow its registration as an legal entity. 

There are three main kinds of cooperatives: producer, consumer and worker. In a producer and consumer cooperative the aims are usually going to be relatively simple. In a worker cooperative the aims are likely to be more complex and its goals need more careful consideration for such an organisation to succeed. If this platform assists in that its key aim mentioned above will be achieved.

Apart from this aim of assisting cooperatives achieve success, most organisations require cooperation between individuals. More specifically they require coordinated effort 'to get things done'. This platform can assist in just that simple aspect (in fact its scope can be restricted by configuration).

In all cooperation 'human nature' is a factor, put simply individuals are strongly influenced by their perception of 'fairness'. People will cooperate if they see the organisational aims aligning with their personal values and aims, and that the effort of all towards achieving the organisational ('common') goals seems fair. So, another aim of this platform is to assist individual cooperators in maintaining that alignment and sense of fairness over time, and so for their cooperation to continue.

Another aspect of fairness is reward. Getting fair rewards from cooperation is probably more flexible than fairness in contributed effort, people will volunteer time to some organisations. Individuals may see a cooperative as a way to gain non-financial rewards that they cannot achieve individually. So in terms of cooperation generally and in forming and operating a cooperative specifically, this flexibility in rewards needs to be considered and managed carefully.

# Conceptual Model

![Conceptual Model](https://github.com/stevecam62/cooperation/blob/master/module-base/documents/cooperation.png)

## Implementation

This project uses the Java web-application framework [Apache Isis](http://isis.apache.org).

It can be enhanced relatively easily by customising its Java Domain Model and/or integrating standard modules from the [Incode Platform](http://platform.incode.org).

## Entities

### Organisation

A formal on informal group of cooperating people. Individual Persons are linked to specific Organisations and view one linked Organisation at a time.

![Organisation Screen](https://github.com/stevecam62/cooperation/blob/master/module-base/images/screen/Organisation.png)

### Aim

A general statement of what an organisation intends to achieve through cooperation.

![Aim Screen](https://github.com/stevecam62/cooperation/blob/master/module-base/images/screen/Aim.png)

### Goal

A specific statement of how one or more of the Aims are going to be achieved.

![Goal Screen](https://github.com/stevecam62/cooperation/blob/master/module-base/images/screen/Goal.png)

### Task

A statement of ongoing or one-of work that can be assigned to individuals for completion.

![Task Screen](https://github.com/stevecam62/cooperation/blob/master/module-base/images/screen/Task.png)

### Plan

A named grouping of Goals with an associated set of Tasks, most likely relating to a time period.

![Plan Screen](https://github.com/stevecam62/cooperation/blob/master/module-base/images/screen/Plan.png)

### Person

An individual cooperating person. Each person has a single user 'account' in the system.

### Effort

A record of a Person's time (interval) spent working on a Task on a specific date.

![Effort Screen](https://github.com/stevecam62/cooperation/blob/master/module-base/images/screen/Effort.png)

### Result

A concrete result of one or more Person's effort working on a Task.

![Result Screen](https://github.com/stevecam62/cooperation/blob/master/module-base/images/screen/Result.png)

### Outcome

An achievement (or not) that can be related back to a goal, likely relating to several Tasks and Results.

![Outcome Screen](https://github.com/stevecam62/cooperation/blob/master/module-base/images/screen/Outcome.png)

### Reward

An allocation of cash (or some other non-monetary thing of value, or a combination) as payment to a Person for their Effort in a period.






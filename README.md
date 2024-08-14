   <h1>Divij's Portfolio Website</h1>
   <p>Welcome to the repository of my portfolio website, <a href="https://www.divij.tech" target="_new">divij.tech</a>. This website serves as a platform for showcasing my projects, sharing blog posts, and providing information about my social media handles and contact details.</p>
   <h2>Description</h2>
   <p>The portfolio website is built using Angular for the frontend and Java for the backend. It follows a monolithic architecture, where the frontend is compiled into static files, and the Java backend provides dynamic data to the frontend.</p>
   <h3>Technologies Used</h3>
   <ul>
      <li>Frontend: Angular 14</li>
      <li>Backend: Java 11</li>
      <li>Framework: Spring</li>
      <li>Persistence: JPA with Spring Data JPA</li>
      <li>Authentication and Authorization: Spring Security </li>
      <li>Database: MySQL</li>
      <li>Database Migration and Version Control: Liquibase</li>
   </ul>
   <p>I chose the Spring framework for the backend as it offers a wide range of functionality out of the box and provides easy integration with other frameworks, especially for security (using Spring Security).</p>
   <p>To handle data persistence, I utilize Spring Data JPA, which simplifies database interactions. Currently, I am using MySQL as the database for its ease of setup and extensive documentation and community support available on platforms like Stack Overflow.</p>
   <h2>Functionality</h2>
   <p>The portfolio website offers the following features:</p>
   <ul>
      <li><strong>Blog</strong>: Visit <a href="https://divij.tech/blog" target="_new">divij.tech/blog</a> to explore my blog posts. This section allows me to share my thoughts, experiences, and insights on various topics.</li>
      <li><strong>Projects</strong>: Check out <a href="https://divij.tech/project" target="_new">divij.tech/project</a> to view my showcased projects. Here, I highlight the projects I have worked on, providing details about their purpose, technologies used, and outcomes achieved.</li>
      <li><strong>Social Media</strong>: Connect with me on various social media platforms. You can find links to my profiles on Twitter, LinkedIn, Instagram, and YouTube.</li>
      <li><strong>Contact Page</strong>: Have a question or want to get in touch? Visit the contact page on <a href="https://divij.tech/contact" target="_new">divij.tech/contact</a> to reach out to me.</li>
   </ul>
   <h2>Getting Started</h2>
   <p>To set up and run the portfolio website locally, follow these steps:</p>
   <ol>
      <li>
         <p>Clone this repository to your local machine:</p>
         <pre><div class="bg-black rounded-md mb-4"><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-shell">git clone https://github.com/divijshrivastava/portfolio.git
</code></div></div></pre>
      </li>
      <li>
         <p>Install the necessary dependencies for the frontend. Navigate to the frontend directory and run:</p>
         <pre><div class="p-4 overflow-y-auto">
         <code class="!whitespace-pre hljs language-nnnn">
         cd portfolio
         npm install
         </code>
         </div></pre>
      </li>
      <li>
         <p>Configure the backend. Set up your MySQL database and update the database configuration in the backend code.</p>
      </li>
      <li>
         <p>Build the frontend. In the frontend directory, run:</p>
         <pre><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-shell">ng build
</code></div></pre>
         <p>This will generate the compiled static files that will be served by the backend.</p>
      </li>
      <li>
         <p>Run the backend. In the root directory, start the backend server using your preferred Java IDE or build tool.</p>
      </li>
      <li>
         <p>Access the portfolio website by opening your browser and navigating to <a href="http://localhost:80" target="_new">http://localhost</a>.</p>
      </li>
   </ol>
   <p>Feel free to customize the website to suit your own needs. You can add more blog posts, showcase additional projects, or modify the design and layout according to your preferences.</p>
   <h2>Contributing</h2>
   <p>Contributions to the portfolio website are welcome! If you'd like to enhance the functionality, fix any issues, or suggest improvements, please follow these steps:</p>
   <ol>
      <li>
         <p>Fork this repository to your GitHub account.</p>
      </li>
      <li>
         <p>Create a new branch with a descriptive name for your contribution.</p>
      </li>
      <li>
         <p>Make your changes, such as adding new features, fixing bugs, or improving the documentation.</p>
      </li>
      <li>
         <p>Commit and push your changes to your forked repository.</p>
      </li>
      <li>
         <p>Submit a pull request, describing the changes you've made and any relevant details.</p>
      </li>
   </ol>
   <p>Your contributions will be reviewed, and upon acceptance, they will be merged into the main</p>

<h2>Commit Message Standard</h2>

<p>Clear commit messages are essential for maintaining a clean and understandable codebase. We follow a standard format to ensure consistency and clarity.</p>

<h3>Structure</h3>

<ul>
  <li>
    <b>Subject (50 characters max):</b>
    <ul>
      <li>Start with a verb in the imperative mood (e.g., "fix", "add", "refactor").</li>
      <li>Briefly describe the change (e.g., "fix contact form submission").</li>
      <li>Optionally, include the affected area in parenthesis (e.g., "fix(contact): contact form submission").</li>
    </ul>
  </li>
  <li>
    <b>Body (optional):</b>
    <ul>
      <li>Provide a more detailed explanation of the change, if needed.</li>
      <li>Explain the reason for the change.</li>
      <li>Reference any related issues or tickets (e.g., "Fixes #123").</li>
      <li>Keep line length around 72 characters.</li>
    </ul>
  </li>
</ul>

<h3>Commit Types</h3>

<ul>
  <li><code>feat</code>: New feature or functionality.</li>
  <li><code>fix</code>: Bug fix or correction.</li>
  <li><code>refactor</code>: Code improvements without changing functionality.</li>
  <li><code>docs</code>: Documentation changes.</li>
  <li><code>style</code>: Code formatting or stylistic changes.</li>
  <li><code>test</code>: Adding or updating tests.</li>
  <li><code>chore</code>: Changes to build process, tooling, or project setup.</li>
</ul>

<h3>Example</h3>

<pre><code>fix(contact): Handle empty email field gracefully
Fixes #141

This commit modifies the contact form validation to provide a clearer error message
when the email field is left empty.
</code></pre>

<p>By following this format, you'll contribute to a well-documented and maintainable codebase.</p>

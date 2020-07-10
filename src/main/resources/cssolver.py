import json
import sys

from pulp import LpProblem, LpMinimize, LpVariable, LpInteger, lpSum, value, LpStatus, splitDict, makeDict


def calculate_patterns(total_roll_length, len_opts, head):
    """
     Recursively calculates the list of options lists for a cutting stock problem. The input
     'tlist' is a pointer, and will be the output of the function call.

     The inputs are:
     totalRollLength - the length of the roll
     lenOpts - a list of the sizes of remaining cutting options
     head - the current list that has been passed down though the recusion

     Returns the list of patterns

     Authors: Bojan Blazevic, Dr Stuart Mitchell    2007
    """
    if len_opts:
        patterns = []
        # take the first option off lenOpts
        opt = len_opts[0]
        for rep in range(int(total_roll_length / opt) + 1):
            # reduce the length
            l = total_roll_length - rep * opt
            h = head[:]
            h.append(rep)

            patterns.extend(calculate_patterns(l, len_opts[1:], h))
    else:
        # end of the recursion
        patterns = [head]
    return patterns


def make_patterns(total_roll_length, len_opts):
    """
     Makes the different cutting patterns for a cutting stock problem.

     The inputs are:
     totalRollLength : the length of the roll
     lenOpts: a list of the sizes of cutting options as strings

     Authors: Antony Phillips, Dr Stuart Mitchell    2007
    """

    # calculatePatterns is called to create a list of the feasible cutting options in 'tlist'
    patterns = calculate_patterns(total_roll_length, len_opts, [])

    # The list 'PatternNames' is created
    PatternNames = []
    for i in range(len(patterns)):
        PatternNames += ["P" + str(i)]

    # The amount of trim (unused material) for each pattern is calculated and added to the dictionary
    # 'trim', with the reference key of the pattern name.
    trim = {}
    for name, pattern in zip(PatternNames, patterns):
        ssum = 0
        for rep, l in zip(pattern, len_opts):
            ssum += rep * l
        trim[name] = total_roll_length - ssum
    # The different cutting lengths are printed, and the number of each roll of that length in each
    # pattern is printed below. This is so the user can see what each pattern contains.
    print("Lens: %s" % len_opts)
    for name, pattern in zip(PatternNames, patterns):
        print(name + "  = %s" % pattern)
    return (PatternNames, patterns, trim)


def valid_pattern(final_patterns, jumbo_length):
    for p in final_patterns:
        sum = 0
        for length, count in p.items():
            sum += length * count
        if sum != jumbo_length:
            return False

    return True


# jumbo = 1500
jumbo = int(sys.argv[1])

# len_opts = [315, 310, 165, 225, 260, 275, 300, 120, 185, 145, 155, 135]

len_opts = sys.argv[2]
len_opts = list(map(int, len_opts.strip('[]').split(',')))

# A dictionary of the demand for each roll length is created
# rollDemand = {'315': 3, '310': 1, '165': 1, '225': 1, '260': 2, '275': 2, '300': 2, '120': 1, '185': 1, '145': 1,
#               '155': 3, '135': 2}

demands = sys.argv[3]
demands = list(map(int, demands.strip('[]').split(',')))
rollDemand = dict(zip([str(k) for k in len_opts], demands))

file_name = sys.argv[4]

(PatternNames, patterns, trim) = make_patterns(jumbo, len_opts)

# The RollData is made into separate dictionaries

patterns = makeDict([PatternNames, len_opts], patterns, 0)

pattVars = LpVariable.dict("Patt", PatternNames, 0, None, LpInteger)

prob = LpProblem("Minimize jumbos usage", LpMinimize)

# objective function
prob += lpSum([pattVars[i] for i in PatternNames])

# minimum demand constraint
for j in len_opts:
    prob += lpSum([pattVars[i] * patterns[i][j] for i in PatternNames]) >= rollDemand[str(j)]

# The problem data is written to an .lp file
prob.writeLP("SpongeRollProblem.lp")
# The problem is solved using PuLP's choice of Solver
prob.solve()
# The status of the solution is printed to the screen
print("Status:", LpStatus[prob.status])
# Each of the variables is printed with it's resolved optimum value

cutting_one_roll_patterns = []

for v in prob.variables():
    if v.varValue > 0:
        cutting_one_roll_patterns.append(patterns[v.name.replace('Patt_', '')])

# The optimised objective function value is printed to the screen
print("Production Costs = ", value(prob.objective))

print('Print patterns')
[print(p) for p in cutting_one_roll_patterns]

is_valid = valid_pattern(cutting_one_roll_patterns, jumbo)

print(is_valid)

with open(file_name, 'w') as f:
    results = cutting_one_roll_patterns if is_valid else \
        'Error occurred. Losses were found in the proposed patterns'
    json.dump(results, f)